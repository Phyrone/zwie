package de.phyrone.zwie.server.web.api1

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.phyrone.zwie.server.data.packets.client.PacketClient
import de.phyrone.zwie.server.data.packets.server.PacketServer
import de.phyrone.zwie.server.user.UserConnection
import io.vertx.ext.web.handler.sockjs.SockJSSocket
import io.vertx.kotlin.coroutines.await
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

class SockJs1UserConnection(
    private val socket: SockJSSocket,
    private val objectMapper: ObjectMapper,
) : UserConnection {

    override val sendChannel: Channel<PacketServer> = Channel()
    override val receiveChannel: Channel<PacketClient> = Channel()


    suspend fun run() = coroutineScope {
        val scope = this
        launch {
            for (packet in sendChannel) {
                socket.write(objectMapper.writeValueAsString(packet)).await()
            }
        }
        socket.handler { buffer ->
            val packet = objectMapper.readValue<PacketClient>(buffer.toString())
            scope.launch { receiveChannel.send(packet) }
        }

        socket.closeHandler {
            sendChannel.close()
            receiveChannel.close()
            cancel("socket closed")
        }
    }

    override fun close() {
        socket.close()
    }

}