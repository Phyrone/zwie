package de.phyrone.zwie.server.data.proto01.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketServer

@JsonTypeName("server/init/init4")
data class PacketServerInit4(
    val serverName: String?,
    val serverDescription: String?,
) : PacketServer
