package de.phyrone.zwie.shared.protocol.coder

import io.ktor.utils.io.core.*

interface PacketDecoder <T>{
    @Throws(IllegalArgumentException::class)
    suspend fun decode(packet: ByteReadPacket): T

}