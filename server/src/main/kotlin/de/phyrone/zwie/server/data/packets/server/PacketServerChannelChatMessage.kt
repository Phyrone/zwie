package de.phyrone.zwie.server.data.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.chat.ChatComponent
import java.util.UUID

@JsonTypeName("server/channel/chat/newmessage")
data class PacketServerChannelChatMessage(
    val sender: UUID,
    val channel: UUID,
    val components: ChatComponent,
) : PacketServer