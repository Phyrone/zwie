package de.phyrone.zwie.server.web.api1

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.cbor.databind.CBORMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.phyrone.zwie.server.data.packets.client.PacketClient
import de.phyrone.zwie.server.data.packets.server.PacketServer
import de.phyrone.zwie.server.user.UserConnection
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import io.vertx.kotlin.coroutines.await
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class Ws1UserConnection(
    val webSocket: ServerWebSocket,
    val objectMapper: ObjectMapper,
) : UserConnection {
    override val sendChannel: Channel<PacketServer> = Channel()
    override val receiveChannel: Channel<PacketClient> = Channel()

    suspend fun run() = coroutineScope {
        val scope = this
        webSocket.accept()

        launch {
            for (packet in sendChannel) {
                webSocket.write(Buffer.buffer(objectMapper.writeValueAsString(packet))).await()
            }
        }

        webSocket.binaryMessageHandler { buffer ->
            val packet = objectMapper.readValue<PacketClient>(buffer.bytes)
            scope.launch {
                receiveChannel.send(packet)
            }
        }

        webSocket.closeHandler {
            sendChannel.close()
            receiveChannel.close()
            cancel("socket closed")
        }
    }

    override fun close() {
        webSocket.close()
    }
}