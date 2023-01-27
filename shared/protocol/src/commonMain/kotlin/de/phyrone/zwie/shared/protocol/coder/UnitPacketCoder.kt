package de.phyrone.zwie.shared.protocol.coder

import de.phyrone.zwie.shared.protocol.coder.PacketCoder
import io.ktor.utils.io.core.*

object UnitPacketCoder : PacketCoder<Unit> {
    override suspend fun decode(packet: ByteReadPacket) = Unit

    override suspend fun encode(packet: Unit): ByteReadPacket = ByteReadPacket(byteArrayOf())
}