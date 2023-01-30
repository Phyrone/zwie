package de.phyrone.zwie.shared.protocol.crypt

import de.phyrone.zwie.shared.crypt.gpg.GPG
import de.phyrone.zwie.shared.crypt.gpg.GPGKeyPriv
import de.phyrone.zwie.shared.crypt.gpg.GPGKeyPub
import io.ktor.utils.io.core.*

class AsymPacketCrypt(
    private val localKey: GPGKeyPriv,
    private val remoteKey: GPGKeyPub
) : PacketCrypt {
    override suspend fun handleIncoming(packet: ByteReadPacket): ByteReadPacket {
        return ByteReadPacket(GPG.signAndEncrypt(packet.readBytes(), remoteKey, localKey))
    }

    override suspend fun handleOutgoing(packet: ByteReadPacket): ByteReadPacket {
        return ByteReadPacket(GPG.decryptAndVerify(packet.readBytes(), localKey, remoteKey))
    }
}