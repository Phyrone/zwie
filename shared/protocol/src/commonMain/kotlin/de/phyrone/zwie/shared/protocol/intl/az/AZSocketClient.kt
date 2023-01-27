package de.phyrone.zwie.shared.protocol.intl.az

import de.phyrone.zwie.shared.protocol.client.getPlatformClientEngine
import de.phyrone.zwie.shared.protocol.crypt.NoOpPacketCrypt
import de.phyrone.zwie.shared.protocol.crypt.PacketCrypt
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*

class AZSocketClient private constructor(
    webSocketSession: WebSocketSession,
    crypt: PacketCrypt,
    val httpClient: HttpClient?
) : AZSocket(
    webSocketSession,
    crypt
) {

    override suspend fun close() {
        super.close()
        httpClient?.close()
    }

    companion object {
        suspend operator fun invoke(url: String, client: HttpClient? = null): AZSocketClient {
            val httpClient = HttpClient(getPlatformClientEngine()) {
                install(WebSockets)
            }
            val webSocket = httpClient.webSocketSession(url) {}
            return AZSocketClient(webSocket, NoOpPacketCrypt, if (client != null) null else httpClient)
        }
    }
}