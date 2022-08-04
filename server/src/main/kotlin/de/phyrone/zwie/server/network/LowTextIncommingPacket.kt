package de.phyrone.zwie.server.network

import com.fasterxml.jackson.annotation.JsonRawValue

data class LowTextIncommingPacket(
    val id: Any /* string or number */,
    @JsonRawValue
    val data: String,
)
