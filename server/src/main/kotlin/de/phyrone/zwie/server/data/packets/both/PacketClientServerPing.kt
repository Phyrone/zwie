package de.phyrone.zwie.server.data.packets.both

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("base/ping")
data class PacketClientServerPing(val pingTime: Long) : PacketClientServer