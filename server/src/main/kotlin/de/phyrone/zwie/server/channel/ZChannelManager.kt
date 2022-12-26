package de.phyrone.zwie.server.channel

interface ZChannelManager {
    suspend fun createChannel(name: String): ZChannel

    suspend fun systemChannelLayout(): ZChannelLayoutNode

    suspend fun createChannelLayout(channel: ZChannel): ZChannelLayoutNode

}