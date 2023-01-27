package de.phyrone.zwie.shared.protocol.coder

import io.ktor.utils.io.core.*

object ByteArrayPacketCoder : PacketCoder<ByteArray> {
    override suspend fun decode(packet: ByteReadPacket): ByteArray = packet.readBytes()

    override suspend fun encode(packet: ByteArray): ByteReadPacket = ByteReadPacket(packet)


}