package de.phyrone.zwie.server.web.proto01

//import de.phyrone.zwie.server.IdentityManager
import com.fasterxml.jackson.dataformat.ion.IonObjectMapper
import com.google.common.io.BaseEncoding
import de.phyrone.zwie.server.data.proto01.packets.PacketClient
import de.phyrone.zwie.server.data.proto01.packets.PacketPing
import de.phyrone.zwie.server.data.proto01.packets.PacketPong
import de.phyrone.zwie.server.data.proto01.packets.PacketServer
import de.phyrone.zwie.server.data.proto01.packets.client.PacketClientInit1
import de.phyrone.zwie.server.data.proto01.packets.client.PacketClientInit3
import de.phyrone.zwie.server.data.proto01.packets.server.PacketServerInit2
import de.phyrone.zwie.server.data.proto01.packets.server.PacketServerInit4
import de.phyrone.zwie.server.event.Proto01SessionSetupEvent
import de.phyrone.zwie.server.user.ZUser
import de.phyrone.zwie.server.utils.loadPGKey
import de.phyrone.zwie.server.utils.logger
import de.phyrone.zwie.server.utils.use
import de.phyrone.zwie.server.user.BackingSessionData
import de.phyrone.zwie.server.user.ZUserManager
import io.ktor.server.plugins.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import org.bouncycastle.util.encoders.Hex
import org.greenrobot.eventbus.EventBus
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.pgpainless.PGPainless
import org.pgpainless.decryption_verification.ConsumerOptions
import java.io.ByteArrayInputStream
import java.security.SecureRandom
import kotlin.jvm.Throws


class Protocol01Socket(
    val webSocket: DefaultWebSocketServerSession,
    val textMode: Boolean = false,
) : KoinComponent, BackingSessionData {


    //private val jsonMapper by inject<ObjectMapper>(named("core::mapper::json"))
    //private val cborMapper by inject<CBORMapper>(named("core::mapper::cbor"))
    private val ionMapper by inject<IonObjectMapper>(named("core::mapper::ion"))
    private val ionMapperBin by inject<IonObjectMapper>(named("core::mapper::ion::binary"))

    private val secureRandom by inject<SecureRandom>()
    private val zUserManager by inject<ZUserManager>()
    private val eventBus by inject<EventBus>()


    private val origin by lazy {
        webSocket.call.request.origin
    }

    private val receiveFlow = MutableSharedFlow<PacketClient>(0, 128, BufferOverflow.SUSPEND)

    suspend fun runLoop(user: ZUser) {
        val session = user.createSession(this)
        try {
            val tasks = Channel<suspend CoroutineScope.() -> Unit>(Channel.UNLIMITED)
            val setup = Proto01SessionSetupEvent(this, session, tasks, receiveFlow, ::send)
            eventBus.post(setup)
            logger.atInfo().log("new client joined (userID=%s address=%s)", user.id, origin.remoteHost)

            try {
                coroutineScope {
                    this.launch { runReceiveLoop(receiveFlow) }
                    this.launch { runPingPongHandler() }
                    //support for later added tasks as long as socket is active/open
                    for (task in tasks) {
                        this.launch { task.invoke(this) }
                    }
                }
            } finally {
                tasks.close()
                logger.atFine().log(
                    "[%s <> %s:%d] connection closed (%s)",
                    origin.remoteHost,
                    origin.host,
                    origin.port,
                    webSocket.closeReason.await()
                )
                logger.atInfo().log("client left (userID=%s address=%s)", user.id, origin.remoteHost)
            }
        } finally {
            session.close()
        }
    }


    private val pongFlow =
        receiveFlow.asSharedFlow().mapNotNull { it as? PacketPong }
    private val pingFlow =
        receiveFlow.asSharedFlow().mapNotNull { it as? PacketPing }

    private suspend fun runPingPongHandler() {
        pingFlow.collect { ping -> send(PacketPong(ping.sequence)) }
    }

    private val sendPingSequence = MutableStateFlow(0L)
    override suspend fun runPingPong() {
        val sequence = sendPingSequence.getAndUpdate { it + 1 }
        coroutineScope {
            launch { send(PacketPing(sequence)) }
            pongFlow.first { it.sequence == sequence }
        }
    }

    private suspend fun runReceiveLoop(receiveFlow: MutableSharedFlow<PacketClient>) {
        logger.atFiner().log("[%s <> %s:%d] start receive loop", origin.remoteHost, origin.host, origin.port)
        for (frame in webSocket.incoming) {
            receiveFlow.emit(frame.readPacket())
        }
        webSocket.cancel("socket closed")
    }


    @Throws(IllegalStateException::class)
    suspend fun handshake(): ZUser {
        try {
            logger.atFine().log(
                "[%s <> %s:%d] websocket established textmode=%s",
                origin.remoteHost,
                origin.host,
                origin.port,
                textMode
            )

            val init1 = withTimeoutOrNull(10_000) {
                webSocket.incoming.receive()
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

            //val challangeString = BaseEncoding.base64().encode(challangeBytes)
            //send(PacketServerInit2(challangeString))
            send(PacketServerInit2(challangeBytes))

            val init3 = withTimeoutOrNull(10_000) {
                webSocket.incoming.receive()
            }?.readPacket() as? PacketClientInit3 ?: error("init 3 timeout")
            val (response, responseGPGResult) = withContext(Dispatchers.IO) {
                ByteArrayInputStream(
                    init3.response.let { response ->
                        when (response) {
                            is ByteArray -> response
                            is String -> response.toByteArray()
                            else -> error("unsupported response type ${response::class}")
                        }
                    }
                ).use { responseStream ->
                    PGPainless.decryptAndOrVerify().onInputStream(responseStream)
                        .withOptions(ConsumerOptions().addVerificationCert(keyRing))
                        .use { decStream ->
                            Pair(
                                //String(decStream.readAllBytes(), Charsets.UTF_8),
                                decStream.readAllBytes(),
                                decStream.apply { close() }.result
                            )
                        }
                }
            }

            val verified = responseGPGResult.isVerified && challangeBytes.contentEquals(response)
            logger.atFine().log(
                "[%s <> %s:%d] handshake: finished 'challange response' verified=%b",
                origin.remoteHost,
                origin.host,
                origin.port,
                verified
            )

            if (!verified) error("challange failed")
            val user = zUserManager.getUser(fingerprint)
            logger.atFine().log(
                "[%s <> %s:%d] handshake: loaded user from database",
                origin.remoteHost,
                origin.host,
                origin.port,
            )
            send(PacketServerInit4("comming soon", null))
            return user

        } catch (e: Exception) {
            logger.atFine().withCause(e)
                .log("[%s <> %s:%d] handshake: failed", origin.remoteHost, origin.host, origin.port)
            throw e
        }
    }


    suspend fun send(packet: PacketServer) {
        logger.atFiner().log("[%s <- %s:%d] send packet %s", origin.remoteHost, origin.host, origin.port, packet)
        if (!webSocket.isActive)
            throw IllegalStateException("Socket is not active is it closed?")

        if (textMode) {
            webSocket.send(ionMapper.writeValueAsString(packet))
        } else {
            webSocket.send(ionMapperBin.writeValueAsBytes(packet))
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
    override suspend fun closeConnection(message: String?) {
        webSocket.close(CloseReason(CloseReason.Codes.NORMAL, message ?: ""))
    }

}

