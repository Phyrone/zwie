package de.phyrone.zwie.shared.protocol.core.ping

import de.phyrone.zwie.shared.protocol.PacketCoder
import io.ktor.utils.io.core.*

object PingCoder : PacketCoder<ZPingPongPacket> {

    override fun encode(packet: ZPingPongPacket): ByteReadPacket {
        val sqn = when (packet) {
            is PacketPing -> packet.time
            is PacketPong -> -packet.time
        }
        return buildPacket {
            writeLong(sqn)
        }
    }

    override fun decode(packet: ByteReadPacket): ZPingPongPacket? {
        val sqn = packet.readLong()
        return when {
            sqn > 0L -> PacketPing(sqn)
            sqn < 0L -> PacketPong(-sqn)
            else -> null
        }
    }

}