package de.phyrone.zwie.server.data.packets.both

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("base/pong")
class PacketClientServerPong(
    @JsonProperty("time")
    val pingTimeStamp: Long,
) : PacketClientServer