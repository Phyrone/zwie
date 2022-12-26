package de.phyrone.zwie.shared.protocol.core.ping

data class PacketPing(
    val time: Long
):ZPingPongPacket
