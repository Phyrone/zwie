package de.phyrone.zwie.server.data.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import java.util.UUID

@JsonTypeName("server/users/remove")
data class PacketServerUserLeave(
    val uuid: UUID,
) : PacketServer
