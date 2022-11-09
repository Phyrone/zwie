package de.phyrone.zwie.server.data.proto01

import de.phyrone.zwie.server.utils.JsonComponent

data class DataChannelLayout(
    val channels: List<DataChannel>,
) : JsonComponent
