package de.phyrone.zwie.server.channel

import de.phyrone.zwie.server.channel.chat.ChatHistory
import de.phyrone.zwie.server.user.ZUserSession
import de.phyrone.zwie.server.utils.MutableSubscriberSet
import de.phyrone.zwie.server.utils.SubscriberSet
import kotlinx.coroutines.flow.MutableStateFlow

interface ZChannel {
    val id: Long
    val name: MutableStateFlow<String>
    val voiceSessions: MutableSubscriberSet<ZUserSession>
    val chat: ChatHistory
}