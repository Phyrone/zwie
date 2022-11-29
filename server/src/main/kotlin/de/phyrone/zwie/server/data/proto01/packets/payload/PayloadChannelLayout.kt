package de.phyrone.zwie.server.data.proto01.packets.payload

import de.phyrone.zwie.server.utils.JsonComponent
import java.util.*

data class PayloadChannelLayout(
    val id: UUID,
    val name: String,
    val description: String,
    val order: Int,
    val subChannels: List<PayloadChannelLayout>,
) : JsonComponent
