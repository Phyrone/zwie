package de.phyrone.zwie.server.data.packets.client

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.chat.ChatComponent

@JsonTypeName("client/chat")
class PacketClientChatMessage(
    val components: List<ChatComponent>,
) : PacketClient