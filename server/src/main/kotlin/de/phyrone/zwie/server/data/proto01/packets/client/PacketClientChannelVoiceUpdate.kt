package de.phyrone.zwie.server.data.proto01.packets.client

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketClient
import java.util.*

@JsonTypeName("client/channel/voice/update")
data class PacketClientChannelVoiceUpdate(
    val channel: UUID? = null,
    val muted: Boolean? = null,
    val deafened: Boolean? = null,
    //comming soon
    //val acceptP2P: Boolean? = null,
) : PacketClient