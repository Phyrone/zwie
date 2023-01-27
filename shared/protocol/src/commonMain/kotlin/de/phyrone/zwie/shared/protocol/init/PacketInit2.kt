package de.phyrone.zwie.shared.protocol.init

import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
data class PacketInit2(
    @ProtoNumber(0x00)
    val version: ULong,
    @ProtoNumber(0x02)
    val publicKey: ByteArray,
    @ProtoNumber(0x03)
    val challenge: ByteArray,
)
