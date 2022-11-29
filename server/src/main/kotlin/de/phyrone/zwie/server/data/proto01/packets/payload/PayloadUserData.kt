package de.phyrone.zwie.server.data.proto01.packets.payload

import de.phyrone.zwie.server.utils.JsonComponent

data class PayloadUserData(
    val id: String,
    val name: String,
) : JsonComponent