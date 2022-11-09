package de.phyrone.zwie.server.data.proto01.packets

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("base/pong")
class PacketClientServerPong(
    @JsonProperty("time")
    val pingTimeStamp: Long,
) : PacketClientServer