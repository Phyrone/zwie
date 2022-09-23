package de.phyrone.zwie.server.data.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import java.util.UUID

@JsonTypeName("server/users/add")
data class PacketServerUserJoin(
    val uuid: UUID,
    val name: String,
) : PacketServer
