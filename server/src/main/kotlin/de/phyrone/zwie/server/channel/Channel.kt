package de.phyrone.zwie.server.channel

import de.phyrone.zwie.server.UniqeEntity

interface Channel : UniqeEntity {
    val name: String
    val persistence: ChannelPersistence

    suspend fun delete()
}