package de.phyrone.zwie.server.data.proto01.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketServer
import java.util.UUID

@JsonTypeName("server/users/remove")
data class PacketServerOtherUserLeave(
    val uuid: UUID,
) : PacketServer
