package de.phyrone.zwie.server.data.proto01.packets

import com.fasterxml.jackson.annotation.JsonTypeName


@JsonTypeName("base/error")
data class PacketLogMessage(
    val level: Level,
    val message: String,
) : PacketClientServer {
    enum class Level {
        DEBUG, WARNING, ERROR, CRITICAL, FATAL
    }
}
