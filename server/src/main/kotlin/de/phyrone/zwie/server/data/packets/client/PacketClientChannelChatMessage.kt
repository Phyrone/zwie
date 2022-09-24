package de.phyrone.zwie.server.data.packets.client

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.chat.ChatComponent
import java.util.UUID

@JsonTypeName("client/channel/chat/send")
class PacketClientChannelChatMessage(
    val channel: UUID,
    val components: ChatComponent,
) : PacketClient