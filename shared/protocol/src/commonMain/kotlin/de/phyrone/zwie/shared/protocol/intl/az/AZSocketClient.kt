package de.phyrone.zwie.shared.protocol.intl.az

import de.phyrone.zwie.shared.crypt.gpg.GPGKeyPriv
import de.phyrone.zwie.shared.protocol.client.getPlatformClientEngine
import de.phyrone.zwie.shared.protocol.crypt.AsymPacketCrypt
import de.phyrone.zwie.shared.protocol.crypt.PacketCrypt
import de.phyrone.zwie.shared.protocol.crypt.SignOnlyPacketCrypt
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*

class AZSocketClient private constructor(
    webSocketSession: WebSocketSession,
    crypt: PacketCrypt,
    private val httpClient: HttpClient,
    private val closeHttpClientAtEnd: Boolean
) : AZSocket(
    webSocketSession,
    crypt
) {

    override suspend fun close() {
        super.close()
        if (closeHttpClientAtEnd)
            httpClient.close()
    }

    companion object {
        suspend operator fun invoke(
            clientKey: GPGKeyPriv, url: String, httpClient: HttpClient? = null
        ): AZSocketClient {

            val fetchedURL = Url(url)
            val secure = when (fetchedURL.protocol) {
                URLProtocol.WSS, URLProtocol.HTTPS -> true
                else -> false
            }

            val httpClientI = httpClient ?: HttpClient(getPlatformClientEngine()) {
                install(WebSockets)
            }
            val webSocket = httpClientI.webSocketSession(
                host = fetchedURL.host,
                port = fetchedURL.port,
                path = fetchedURL.encodedPath,
            ) {}

            println("socket working -> starting handshake...")
            val (_, serverKey) = try {
                webSocket.runHandshakeClient(clientKey, secure)
            } catch (e: Exception) {
                webSocket.close()
                println("handshake failed $e")
                throw e
            }
            println("handshake done")

            val crypt = if (secure) SignOnlyPacketCrypt(clientKey, serverKey) else
                AsymPacketCrypt(clientKey, serverKey)

            return AZSocketClient(webSocket, crypt, httpClientI, httpClient == null)
        }
    }
}