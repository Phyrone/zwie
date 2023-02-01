package de.phyrone.zwie.shared.protocol.init

import io.ktor.utils.io.core.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
data class PacketInit2(
    @ProtoNumber(0x00)
    val version: Int,
    @ProtoNumber(0x02)
    val publicKey: ByteArray,
    @ProtoNumber(0x03)
    val challenge: ByteArray,
){

    fun toByteArray() = buildPacket {
        writeInt(version)
        writeInt(publicKey.size)
        writeFully(publicKey)
        writeInt(challenge.size)
        writeFully(challenge)
    }.readBytes()

    companion object{
        fun fromByteArray(byteArray: ByteArray): PacketInit2 {
            val packet = ByteReadPacket(byteArray)
            val version = packet.readInt()
            val publicKeySize = packet.readInt()
            val publicKey = packet.readBytes(publicKeySize)
            val challengeSize = packet.readInt()
            val challenge = packet.readBytes(challengeSize)
            return PacketInit2(version, publicKey, challenge)
        }
    }
}
