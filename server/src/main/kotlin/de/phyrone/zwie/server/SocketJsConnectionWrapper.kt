package de.phyrone.zwie.server

import de.phyrone.zwie.shared.PacketSerializer
import de.phyrone.zwie.shared.ZPacket
import io.vertx.ext.web.handler.sockjs.SockJSSocket
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

class SocketJsConnectionWrapper(
    val sockJsConnection: SockJSSocket,
) {

    private var closed = false
    private val receiveChannel = Channel<ZPacket>(onBufferOverflow = BufferOverflow.SUSPEND)

    init {
        sockJsConnection.handler { message ->
            val packet = PacketSerializer.deserialize(message.toString())
            runBlocking {
                receiveChannel.send(packet)
            }
        }
        sockJsConnection.closeHandler {
            runBlocking {
                receiveChannel.close()
            }
        }
    }

    suspend fun receive() = receiveChannel.receive()

    fun send(packet: ZPacket) {
        sockJsConnection.write(PacketSerializer.serialize(packet))
    }
}