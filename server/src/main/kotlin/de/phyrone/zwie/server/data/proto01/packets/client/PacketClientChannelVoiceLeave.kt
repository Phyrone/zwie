package de.phyrone.zwie.server.data.proto01.packets.client

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketClient
import java.util.*

@JsonTypeName("client/channel/voice/leave")
data class PacketClientChannelVoiceLeave(
    val channel: UUID? = null,
) : PacketClient
