package de.phyrone.zwie.server.vserver

import kotlinx.coroutines.flow.MutableStateFlow

interface ZChannelLayoutNode {

    val channel: ZChannel

    suspend fun addSubChannel(zChannel: ZChannel): ZChannelLayoutNode

    suspend fun removeSubChannel(zChannel: ZChannel)

    suspend fun subChannels(): MutableStateFlow<List<ZChannelLayoutNode>>
}