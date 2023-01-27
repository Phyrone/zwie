package de.phyrone.zwie.shared.protocol.crypt

import de.phyrone.zwie.shared.crypt.gpg.GPGKeyPriv
import de.phyrone.zwie.shared.crypt.gpg.GPGKeyPub
import io.ktor.utils.io.core.*

interface PacketCrypt {

    suspend fun handleIncoming(packet: ByteReadPacket): ByteReadPacket
    suspend fun handleOutgoing(packet: ByteReadPacket): ByteReadPacket

}