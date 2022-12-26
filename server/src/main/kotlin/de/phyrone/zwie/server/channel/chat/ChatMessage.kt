package de.phyrone.zwie.server.channel.chat

import de.phyrone.zwie.server.data.chat.ChatComponent
import de.phyrone.zwie.server.user.ZUser

data class ChatMessage(
    val id: Long,
    val user: ZUser,
    val message: ChatComponent,
)