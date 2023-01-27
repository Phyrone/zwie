package de.phyrone.zwie.shared.protocol.userlist

import de.phyrone.zwie.shared.protocol.coder.PacketCoder
import io.ktor.utils.io.core.*
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf

object UserListCoder : PacketCoder<PacketUserList> {
    private val proto = ProtoBuf {
        encodeDefaults = false
    }

    override suspend fun decode(packet: ByteReadPacket): PacketUserList = proto.decodeFromByteArray(packet.readBytes())

    override suspend fun encode(packet: PacketUserList): ByteReadPacket = ByteReadPacket(proto.encodeToByteArray(packet))
}