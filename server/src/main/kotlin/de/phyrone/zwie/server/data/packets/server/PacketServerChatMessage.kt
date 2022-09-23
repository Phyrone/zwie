package de.phyrone.zwie.server.data.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.chat.ChatComponent
import java.util.UUID

@JsonTypeName("server/chat")
data class PacketServerChatMessage(
    val sender: UUID,
    val components: List<ChatComponent>,
) : PacketServer