package de.phyrone.zwie.shared.protocol.control

data class PacketChannelOpen(

    val channelID: ULong,
    val name: String
)