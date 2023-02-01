package de.phyrone.zwie.shared.protocol.init

import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
data class PacketInit3(
    val challengeResponse: ByteArray
){

    fun toByteArray() = challengeResponse

    companion object{
        fun fromByteArray(byteArray: ByteArray): PacketInit3 {
            return PacketInit3(byteArray)
        }
    }
}
