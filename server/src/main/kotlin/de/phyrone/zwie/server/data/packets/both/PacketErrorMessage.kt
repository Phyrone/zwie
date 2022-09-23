package de.phyrone.zwie.server.data.packets.both

import com.fasterxml.jackson.annotation.JsonTypeName


@JsonTypeName("base/error")
data class PacketErrorMessage(
    val level: Level,
    val message: String,
) : PacketClientServer {
    enum class Level {
        DEBUG, WARNING, ERROR, CRITICAL
    }
}
