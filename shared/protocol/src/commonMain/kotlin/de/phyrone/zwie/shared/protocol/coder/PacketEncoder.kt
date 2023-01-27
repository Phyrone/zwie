package de.phyrone.zwie.shared.protocol.coder

import io.ktor.utils.io.core.*

interface PacketEncoder<T> {

    @Throws(IllegalArgumentException::class)
    suspend fun encode(packet: T): ByteReadPacket
}