package de.phyrone.zwie.server.data.proto01.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketServer
import java.util.*

@JsonTypeName("server/channel/voice/rtc")
data class PacketServerChannelVoiceRTC(
    val channel: UUID,
    val user: UUID,
    val rtc: String,
) : PacketServer
