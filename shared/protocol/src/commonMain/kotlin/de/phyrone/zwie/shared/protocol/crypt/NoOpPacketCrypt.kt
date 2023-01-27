package de.phyrone.zwie.shared.protocol.crypt

import io.ktor.utils.io.core.*

object NoOpPacketCrypt : PacketCrypt {
    override suspend fun handleIncoming(packet: ByteReadPacket): ByteReadPacket = packet

    override suspend fun handleOutgoing(packet: ByteReadPacket): ByteReadPacket = packet
}