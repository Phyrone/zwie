package de.phyrone.zwie.server.user

import de.phyrone.zwie.server.UniqeEntity
import de.phyrone.zwie.server.channel.ChannelLayout
import de.phyrone.zwie.server.chat.ChatParticipant

interface User : ChatParticipant, UniqeEntity {
    suspend fun setChannelLayout(layout: ChannelLayout?)
    suspend fun resetChannelLayout()
    suspend fun kick(message: String? = null)
}