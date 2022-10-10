package de.phyrone.zwie.server.data

import de.phyrone.zwie.server.utils.JsonComponent
import java.util.UUID

data class DataChannel(
    val id: UUID,
    val name: String,
) : JsonComponent