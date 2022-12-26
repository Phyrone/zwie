package de.phyrone.zwie.shared.protocol

import io.ktor.utils.io.core.*

interface PacketCoder<T : ZProtocolPacket> {
    fun encode(packet: T): ByteReadPacket?
    fun decode(packet: ByteReadPacket): T?

}