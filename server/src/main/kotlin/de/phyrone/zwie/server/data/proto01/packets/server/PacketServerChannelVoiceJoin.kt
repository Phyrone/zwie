package de.phyrone.zwie.server.data.proto01.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketServer
import java.util.*

@JsonTypeName("server/channel/voice/join")
class PacketServerChannelVoiceJoin(
    val channel: UUID,
    val user: UUID,
) : PacketServer