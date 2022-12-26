package de.phyrone.zwie.shared.protocol.core

import kotlinx.serialization.Serializable

@Serializable
data class PacketFlashCodec(
    val name: String? = null,
)