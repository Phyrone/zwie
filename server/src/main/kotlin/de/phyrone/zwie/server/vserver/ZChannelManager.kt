package de.phyrone.zwie.server.vserver

interface ZChannelManager {
    suspend fun createChannel(name: String): ZChannel

    suspend fun defaultChannelLayout(): ZChannelLayoutNode

    suspend fun createChannelLayout(channel: ZChannel): ZChannelLayoutNode

}