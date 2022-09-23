package de.phyrone.zwie.server.data.packets.both

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("ping")
data class PacketClientServerPing(val pingTime: Long) : PacketClientServer