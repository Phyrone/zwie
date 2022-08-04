package de.phyrone.zwie.server.network

data class LowTextOutgoingPacket(
    val id: Any, /* string or number */
    val data: Any,
)
