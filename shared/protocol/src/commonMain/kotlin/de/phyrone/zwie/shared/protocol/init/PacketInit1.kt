package de.phyrone.zwie.shared.protocol.init

import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
data class PacketInit1(
    @ProtoNumber(0x00)
    val version: ULong,
    @ProtoNumber(0x02)
    val publicKey: ByteArray,

    @ProtoNumber(0x0F)
    val connectionAlreadyEncrypted: Boolean,
)
