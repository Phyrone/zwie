package de.phyrone.zwie.server.data.proto01.packets.client

import de.phyrone.zwie.server.data.proto01.packets.PacketClient
import java.util.*

data class PacketClientChannelVoiceRTC(
    val channel: UUID? = null,
    val rtc: String,
) : PacketClient
