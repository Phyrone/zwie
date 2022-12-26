package de.phyrone.zwie.shared.protocol.core.user

import de.phyrone.zwie.shared.protocol.PacketCoder
import io.ktor.utils.io.core.*

object UserCoder : PacketCoder<ZUserPacket> {
    override fun encode(packet: ZUserPacket): ByteReadPacket? {
        TODO("Not yet implemented")
    }

    override fun decode(packet: ByteReadPacket): ZUserPacket? {
        TODO("Not yet implemented")
    }
}