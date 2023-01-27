package de.phyrone.zwie.shared.protocol.control

data class PacketChannelOpenAcceptedResponse(
    val channelName: String,
    val channelID: ULong,
)