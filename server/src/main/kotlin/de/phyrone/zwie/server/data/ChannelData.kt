package de.phyrone.zwie.server.data

import de.phyrone.zwie.server.utils.JsonComponent
import java.util.UUID

data class ChannelData(
    val name: String,
    val id: UUID,
) : JsonComponent
