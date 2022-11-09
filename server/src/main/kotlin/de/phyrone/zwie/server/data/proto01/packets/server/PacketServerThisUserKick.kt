package de.phyrone.zwie.server.data.proto01.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketServer

@JsonTypeName("server/users/this/kick")
data class PacketServerThisUserKick(
    val reason: String?,
) : PacketServer
