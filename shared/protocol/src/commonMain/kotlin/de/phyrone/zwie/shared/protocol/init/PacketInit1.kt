package de.phyrone.zwie.shared.protocol.init

import io.ktor.utils.io.core.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
data class PacketInit1(
    @ProtoNumber(0x00)
    val version: Int,
    @ProtoNumber(0x02)
    val publicKey: ByteArray,
    @ProtoNumber(0x0F)
    val connectionAlreadyEncrypted: Boolean,
) {

    fun toByteArray(): ByteArray = buildPacket {
        writeInt(version)
        writeInt(publicKey.size)
        writeFully(publicKey)
        writeByte(if(connectionAlreadyEncrypted) 0x01 else 0x00)
    }.readBytes()

    companion object{
        fun fromByteArray(bytes: ByteArray): PacketInit1 {
            val packet = ByteReadPacket(bytes)
            val version = packet.readInt()
            val publicKeySize = packet.readInt()
            val publicKey = packet.readBytes(publicKeySize)
            val connectionAlreadyEncrypted = packet.readByte() == 0x01.toByte()
            return PacketInit1(version, publicKey, connectionAlreadyEncrypted)
        }
    }

}
