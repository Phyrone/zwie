package de.phyrone.zwie.server.network

data class LowTextOutgoingPacket(
    val id: String,
    val payload: Any,
)
