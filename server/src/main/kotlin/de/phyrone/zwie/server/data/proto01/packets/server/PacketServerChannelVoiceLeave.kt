package de.phyrone.zwie.server.data.proto01.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketServer
import java.util.UUID

@JsonTypeName("server/channel/voice/leave")
data class PacketServerChannelVoiceLeave(
    val channel: UUID,
    val user: UUID,
) : PacketServer