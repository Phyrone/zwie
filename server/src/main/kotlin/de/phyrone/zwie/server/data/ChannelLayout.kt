package de.phyrone.zwie.server.data

data class ChannelLayout(
    val data: ChannelData,
    val childs: List<ChannelLayout>,
)
