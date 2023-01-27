@file:Suppress("ArrayInDataClass")

package de.phyrone.zwie.shared.protocol.intl

import kotlinx.serialization.Serializable

@Serializable
data class PacketInit(
    val identity: ByteArray,
    val nickname: String, //client nickname or server name
    val requireEncryption: Boolean,
    val timestamp: Long,
)