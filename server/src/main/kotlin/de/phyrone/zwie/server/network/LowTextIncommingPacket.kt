package de.phyrone.zwie.server.network

import com.fasterxml.jackson.annotation.JsonRawValue

data class LowTextIncommingPacket(
    val id: String,
    @JsonRawValue
    val data: String,
)
