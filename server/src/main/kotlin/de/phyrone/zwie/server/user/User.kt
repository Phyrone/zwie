package de.phyrone.zwie.server.user

import de.phyrone.zwie.server.UniqeEntity
import de.phyrone.zwie.server.channel.ChannelLayout
import de.phyrone.zwie.server.chat.ChatParticipant
import java.io.Closeable

interface User : ChatParticipant, UniqeEntity,Closeable {
    suspend fun setChannelLayout(layout: ChannelLayout?)
    suspend fun resetChannelLayout()
    suspend fun kick(reason: String? = null)

}