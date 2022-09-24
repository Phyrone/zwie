package de.phyrone.zwie.server.data.packets.both

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("base/ping")
data class PacketClientServerPing(
    @JsonProperty("time")
    val pingTime: Long,
) : PacketClientServer