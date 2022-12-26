package de.phyrone.zwie.shared.protocol.core.control

import de.phyrone.zwie.shared.protocol.PacketCoder
import io.ktor.utils.io.core.*

object ControlPacketCoder : PacketCoder<ZPacketControl> {
    override fun encode(packet: ZPacketControl): ByteReadPacket? {
        TODO("Not yet implemented")
    }

    override fun decode(packet: ByteReadPacket): ZPacketControl? {
        TODO("Not yet implemented")
    }
}