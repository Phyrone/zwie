package de.phyrone.zwie.server.web.proto01a

import de.phyrone.zwie.shared.protocol.crypt.PacketCrypt
import de.phyrone.zwie.shared.protocol.intl.az.AZSocket
import io.ktor.websocket.*

class AzServerSocket(
    websocket: WebSocketSession,
    packetCrypt: PacketCrypt,
) : AZSocket(websocket, packetCrypt)