package de.phyrone.zwie.server.data.proto01.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketServer
import java.util.*

@JsonTypeName("server/channel/voice/update")
data class PacketServerChannelVoiceUpdate(
    val channel: UUID,
    val user: UUID,
    val muted: Boolean,
    val deafened: Boolean,
) : PacketServer