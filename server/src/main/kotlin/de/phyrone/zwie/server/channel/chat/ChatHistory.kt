package de.phyrone.zwie.server.channel.chat

import de.phyrone.zwie.server.data.chat.ChatComponent
import de.phyrone.zwie.server.user.ZUser
import kotlinx.coroutines.flow.Flow

interface ChatHistory {

    suspend fun addMessage(sender: ZUser, message: ChatComponent)

    suspend fun getMessages(start: Long, size: Int): List<ChatMessage>

    suspend fun subscribe(): Flow<ChatMessage>

}