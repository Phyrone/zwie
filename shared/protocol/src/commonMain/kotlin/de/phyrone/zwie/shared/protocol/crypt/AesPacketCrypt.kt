package de.phyrone.zwie.shared.protocol.crypt

import io.ktor.utils.io.core.*

class AesPacketCrypt(
    val sharedIV: ByteArray
) : PacketCrypt {



    override suspend fun handleIncoming(packet: ByteReadPacket): ByteReadPacket {

        TODO("Not yet implemented")
    }

    override suspend fun handleOutgoing(packet: ByteReadPacket): ByteReadPacket {
        TODO("Not yet implemented")
    }


}