package de.phyrone.zwie.server.data.packets.both

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("base/pong")
class PacketClientServerPong(val pingTimeStame: Long) : PacketClientServer