package de.phyrone.zwie.shared.protocol.core.ping

data class PacketPong(
    val time: Long,
) : ZPingPongPacket