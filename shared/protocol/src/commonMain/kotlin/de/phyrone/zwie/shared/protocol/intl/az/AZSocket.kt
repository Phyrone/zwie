package de.phyrone.zwie.shared.protocol.intl.az

import de.phyrone.zwie.shared.protocol.ZSocket
import de.phyrone.zwie.shared.protocol.coder.PacketDecoder
import de.phyrone.zwie.shared.protocol.coder.PacketEncoder
import de.phyrone.zwie.shared.protocol.control.ControlPacketType
import de.phyrone.zwie.shared.protocol.crypt.PacketCrypt
import de.phyrone.zwie.shared.protocol.intl.SizeDescriptor
import de.phyrone.zwie.shared.protocol.rpc.*
import io.ktor.utils.io.core.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlin.math.max
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


abstract class AZSocket(
    val webSocket: WebSocketSession,
    val crypt: PacketCrypt,
) : ZSocket {

    private val scope = CoroutineScope(webSocket.coroutineContext)

    /* IN */

    init {
        scope.launch { listen() }
        scope.launch { heartbeat() }
    }

    private suspend fun listen() {
        for (frame in webSocket.incoming) {
            if (frame !is Frame.Binary) continue
            handleReceive(ByteReadPacket(frame.readBytes()))
        }
    }

    private suspend fun handleReceive(rawPacket: ByteReadPacket) {
        val header = PacketHeader.fromByte(rawPacket.readByte())
        val packet = if (header.secure) crypt.handleIncoming(rawPacket) else rawPacket

        when (header) {
            is PacketHeader.Control -> handleIncommingControl(header, packet)
            is PacketHeader.Ping -> handleIncommingPing(header, packet)
            is PacketHeader.Pong -> handleIncommingPong(header, packet)
            is PacketHeader.Heartbeat -> {/* for now do nothing */
            }
            is PacketHeader.Channel -> TODO()
        }

    }

    private suspend fun handleIncommingControl(header: PacketHeader.Control, packet: ByteReadPacket) {
        val typeID = packet.readShort().toUShort()
        val type =
            ControlPacketType.fromID(typeID) ?: error("there is no known control type '${typeID /* TODO to hex */}'")
        when (type) {
            ControlPacketType.CHANNEL_OPEN_REQUEST -> TODO()
            ControlPacketType.CHANNEl_OPEN_ACCEPTED -> TODO()
            ControlPacketType.CHANNEL_OPEN_REJECTED -> TODO()
            ControlPacketType.CHANNEL_CLOSE -> TODO()
            ControlPacketType.READY -> {
                val iteration = packet.readInt().toUInt()
                remoteReady.update { max(it, iteration) }
            }

            ControlPacketType.CLOSE -> TODO()
            ControlPacketType.ERROR -> TODO()
            ControlPacketType.DEBUG_LOG -> TODO()
        }
    }

    private suspend fun handleIncommingPing(header: PacketHeader.Ping, packet: ByteReadPacket) {
        sendPacket(PacketHeader.Pong, packet)
    }

    private suspend fun handleIncommingPong(header: PacketHeader.Pong, packet: ByteReadPacket) {
        val id = packet.readShort().toUShort()
        pongFlow.emit(id)
    }


    /* OUT */

    private suspend fun sendPacket(header: PacketHeader, packet: ByteReadPacket) {
        val headerByte = header.toByte()
        val payload = if (header.secure) {
            crypt.handleOutgoing(packet)

        } else packet
        val fullPacket = buildPacket {
            writeByte(headerByte)
            writePacket(payload)
        }
        webSocket.send(fullPacket.readBytes())
    }


    /* Higher Level */

    private val currentPingSequence = MutableStateFlow(0)
    private val pongFlow = MutableSharedFlow<UShort>()
    private val pingLimit = Semaphore(Short.MAX_VALUE.toInt())

    @OptIn(ExperimentalTime::class)
    override suspend fun ping(): Duration = pingLimit.withPermit {
        measureTime {
            val sequence = currentPingSequence.updateAndGet { (it + 1) % UShort.MAX_VALUE.toInt() }
            coroutineScope {
                launch {
                    sendPacket(PacketHeader.Ping, buildPacket {
                        writeShort(sequence.toShort())
                    })
                }
                pongFlow.first { it.toInt() == sequence }
            }
        }
    }

    suspend fun heartbeat() {
        while (true) {
            sendPacket(PacketHeader.Control, buildPacket { })
            delay(5_000)
        }
    }


    private val localReady = MutableStateFlow(0u)
    private val remoteReady = MutableStateFlow(0u)
    override suspend fun ready(iteration: UInt) {
        val sendIteration = localReady.updateAndGet { max(it, iteration) }
        sendPacket(PacketHeader.Control, buildPacket {
            writeShort(ControlPacketType.READY.id.toShort())
            writeInt(sendIteration.toInt())
        })
        remoteReady.first { it >= iteration }
    }

    override suspend fun <In, Out> openChannel(
        name: String, incomming: PacketDecoder<In>, outgoint: PacketEncoder<Out>
    ): ZChannel<In, Out> {
        TODO("Not yet implemented")
    }

    override suspend fun close() {
        scope.cancel()
        webSocket.close(CloseReason(CloseReason.Codes.NORMAL, ""))
    }


    inner class ZChannelImpl<ChannelIN, ChannelOUT>(
        val inID: ULong,
        val outID: ULong,
        val inDecoder: PacketDecoder<ChannelIN>,
        val outEncoder: PacketEncoder<ChannelOUT>,
        private val inChannel: Channel<ChannelIN> = Channel(),
        private val outChannel: Channel<ChannelOUT> = Channel()
    ) : SendChannel<ChannelOUT> by outChannel, ReceiveChannel<ChannelIN> by inChannel, ZChannel<ChannelIN, ChannelOUT> {
        override val incomming: ReceiveChannel<ChannelIN> = inChannel
        override val outgoing: SendChannel<ChannelOUT> = outChannel
        private val outSizeDescriptor = SizeDescriptor.fromSample(outID)
        suspend fun run() = coroutineScope {
            launch {
                outChannel.consumeEach { out ->
                    val payload = outEncoder.encode(out)
                    sendPacket(PacketHeader.Channel(outSizeDescriptor), buildPacket {
                        writePacket(outSizeDescriptor.toPacket(outID))
                        writePacket(payload)
                    })
                }
            }
        }

        suspend fun receivePacket(packet: ByteReadPacket) {
            val decoded = inDecoder.decode(packet)
            inChannel.send(decoded)
        }

        override fun close() {
            outChannel.close()
            inChannel.close()
        }


    }


}