package de.phyrone.zwie.server.data.proto01.packets.client

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketClient
import java.util.*

@JsonTypeName("client/channel/voice/join")
data class PacketClientChannelVoiceJoin(
    val channel: UUID,
    //this is inteded for bots wich can join multiple channels at once so you dont need multiple instances
    /**
     * true: if the client is already present in another channel it will connect to the new channel as well
     * false: if the client is already present in another channel it will disconnect from the old channel
     */
    val allowMultiJoin: Boolean = false,
) : PacketClient
