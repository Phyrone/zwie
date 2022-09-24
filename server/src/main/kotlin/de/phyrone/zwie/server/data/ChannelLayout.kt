package de.phyrone.zwie.server.data

import de.phyrone.zwie.server.utils.JsonComponent

data class ChannelLayout(
    val data: ChannelData,
    val childs: List<ChannelLayout>,
) : JsonComponent
