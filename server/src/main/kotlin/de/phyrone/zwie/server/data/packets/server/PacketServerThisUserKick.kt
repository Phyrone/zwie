package de.phyrone.zwie.server.data.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("server/users/this/kick")
data class PacketServerThisUserKick(
    val reason: String?,
) : PacketServer
