package de.phyrone.zwie.server.data.proto01.packets

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("pong")
class PacketPong(
    val sequence: Long,
) : PacketClientServer