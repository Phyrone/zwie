package de.phyrone.zwie.shared.protocol.core.channel

import de.phyrone.zwie.shared.protocol.PacketCoder
import io.ktor.utils.io.core.*

object ChannelCoder : PacketCoder<ZChannelPacket> {
    override fun encode(packet: ZChannelPacket): ByteReadPacket? {
        TODO("Not yet implemented")
    }

    override fun decode(packet: ByteReadPacket): ZChannelPacket? {
        TODO("Not yet implemented")
    }
}