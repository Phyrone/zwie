package de.phyrone.zwie.server.web.proto01

//import de.phyrone.zwie.server.IdentityManager
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.ion.IonObjectMapper
import com.google.common.io.BaseEncoding
import de.phyrone.zwie.server.data.proto01.packets.PacketClient
import de.phyrone.zwie.server.data.proto01.packets.PacketServer
import de.phyrone.zwie.server.data.proto01.packets.client.PacketClientInit1
import de.phyrone.zwie.server.data.proto01.packets.client.PacketClientInit3
import de.phyrone.zwie.server.data.proto01.packets.server.PacketServerInit2
import de.phyrone.zwie.server.data.proto01.packets.server.PacketServerInit4
import de.phyrone.zwie.server.database.entity.UserEntity
import de.phyrone.zwie.server.event.UserSessionCreatedEven
import de.phyrone.zwie.server.utils.loadPGKey
import de.phyrone.zwie.server.utils.logger
import de.phyrone.zwie.server.utils.use
import de.phyrone.zwie.server.vserver.BackingSessionData
import de.phyrone.zwie.server.vserver.ZUserManager
import io.ktor.server.plugins.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import org.bouncycastle.util.encoders.Hex
import org.greenrobot.eventbus.EventBus
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.pgpainless.PGPainless
import org.pgpainless.decryption_verification.ConsumerOptions
import java.io.ByteArrayInputStream
import java.security.SecureRandom
import kotlin.jvm.Throws


class Protocol01Socket(
    private val socket: DefaultWebSocketServerSession,
    private var textMode: Boolean = false,
) : KoinComponent, BackingSessionData {


    //private val jsonMapper by inject<ObjectMapper>(named("core::mapper::json"))
    //private val cborMapper by inject<CBORMapper>(named("core::mapper::cbor"))
    private val ionMapper by inject<IonObjectMapper>(named("core::mapper::ion"))
    private val ionMapperBin by inject<IonObjectMapper>(named("core::mapper::ion::binary"))

    private val database by inject<Database>()
    private val secureRandom by inject<SecureRandom>()
    private val zUserManager by inject<ZUserManager>()
    private val eventBus by inject<EventBus>()


    private val origin by lazy {
        socket.call.request.origin
    }


    suspend fun runLoop(user: UserEntity) {
        zUserManager.addUser(user, this).use { session ->
            val receiveFlow = MutableSharedFlow<PacketClient>(0, 128, BufferOverflow.SUSPEND)
            val sendFlow = MutableSharedFlow<PacketServer>(0, 128, BufferOverflow.SUSPEND)

            val tasks = mutableListOf<suspend CoroutineScope.() -> Unit>()
            val setup = UserSessionCreatedEven(session, tasks, receiveFlow, sendFlow)
            eventBus.post(setup)

            logger.atInfo().log("new client joined (uid=%s address=%s)", user.id.value, origin.remoteHost)

            try {
                coroutineScope {
                    this.launch { runReceiveLoop(receiveFlow) }
                    this.launch { runSendLoop(sendFlow) }
                    tasks.map { task -> this.launch { task.invoke(this) } }
                }
            } finally {
                logger.atFine().log(
                    "[%s <> %s:%d] connection closed (%s)",
                    origin.remoteHost,
                    origin.host,
                    origin.port,
                    socket.closeReason.await()
                )
                logger.atInfo().log("client left (uid=%s address=%s)", user.id.value, origin.remoteHost)
            }
        }
    }

    private suspend fun runReceiveLoop(receiveFlow: MutableSharedFlow<PacketClient>) {
        logger.atFiner().log("[%s <> %s:%d] start receive loop", origin.remoteHost, origin.host, origin.port)
        for (frame in socket.incoming) {
            receiveFlow.emit(frame.readPacket())
        }
        socket.cancel("socket closed")
    }

    private suspend fun runSendLoop(sendFlow: MutableSharedFlow<PacketServer>): Nothing {
        logger.atFiner().log("[%s <> %s:%d] start send loop", origin.remoteHost, origin.host, origin.port)
        sendFlow.collect { packet -> send(packet) }
    }

    @Throws(IllegalStateException::class)
    suspend fun handshake(): UserEntity {
        logger.atFine().log(
            "[%s <> %s:%d] websocket established textmode=%s",
            origin.remoteHost,
            origin.host,
            origin.port,
            textMode
        )

        val init1 = withTimeoutOrNull(10_000) {
            socket.incoming.receive()
        }?.readPacket() as? PacketClientInit1 ?: error("init 1 timeout")

        logger.atFine().log(
            "[%s -> %s:%d] handshake: got 'init 1' nickname=%s pubkey=***",
            origin.remoteHost,
            origin.host,
            origin.port,
            init1.nickname,
        )


        val keyRing = loadPGKey(init1.identity) ?: error("invalid identity")

        val fingerprint = Hex.toHexString(keyRing.publicKey.fingerprint)
        logger.atFiner()
            .log(
                "[%s <> %s:%d] handshake: fingerprint is '%s'",
                origin.remoteHost,
                origin.host,
                origin.port,
                fingerprint
            )

        val challangeBytes = ByteArray(512)
        secureRandom.nextBytes(challangeBytes)

        val challangeString = BaseEncoding.base64().encode(challangeBytes)
        send(PacketServerInit2(challangeString))

        val init3 = withTimeoutOrNull(10_000) {
            socket.incoming.receive()
        }?.readPacket() as? PacketClientInit3 ?: error("init 3 timeout")

        val (response, responseGPGResult) = withContext(Dispatchers.IO) {
            ByteArrayInputStream(init3.response.toByteArray()).use { responseStream ->
                PGPainless.decryptAndOrVerify().onInputStream(responseStream)
                    .withOptions(ConsumerOptions().addVerificationCert(keyRing))
                    .use { decStream ->
                        Pair(
                            String(decStream.readAllBytes(), Charsets.UTF_8),
                            decStream.apply { close() }.result
                        )
                    }
            }
        }

        val verified = responseGPGResult.isVerified && challangeString == response
        logger.atFine().log(
            "[%s <> %s:%d] handshake: finished 'challange response' verified=%b",
            origin.remoteHost,
            origin.host,
            origin.port,
            verified
        )

        if (!verified) error("challange failed")

        val user = newSuspendedTransaction(Dispatchers.IO, database) {
            val user = UserEntity.findById(fingerprint) ?: UserEntity.new(fingerprint) {}
            user.name = init1.nickname
            return@newSuspendedTransaction user
        }
        logger.atFine().log(
            "[%s <> %s:%d] handshake: loaded user from database",
            origin.remoteHost,
            origin.host,
            origin.port,
        )

        send(PacketServerInit4("comming soon", null))
        return user
    }


    suspend fun send(packet: PacketServer) {
        logger.atFiner().log("[%s <- %s:%d] send packet %s", origin.remoteHost, origin.host, origin.port, packet)
        if (!socket.isActive)
            throw IllegalStateException("Socket is not active is it closed?")

        if (textMode) {
            socket.send(ionMapper.writeValueAsString(packet))
        } else {
            socket.send(ionMapperBin.writeValueAsBytes(packet))
        }
    }


    private fun Frame.readPacket(): PacketClient = when (this) {
        is Frame.Text -> ionMapper.readValue(this.readText(), PacketClient::class.java)
        is Frame.Binary -> ionMapper.readValue(this.readBytes(), PacketClient::class.java)
        else -> error("Unsupported Frame Type ${this.frameType.name}")
    }.also { decodedPacket ->
        logger.atFiner()
            .log("[%s -> %s:%d] decoded packet: %s", origin.remoteHost, origin.host, origin.port, decodedPacket)
    }

    companion object {
        private val logger = logger()
    }

    override val localAddress by lazy { Pair(origin.host, origin.port) }
    override val remoteAddress by lazy { Pair(origin.remoteHost, -1) }

}

