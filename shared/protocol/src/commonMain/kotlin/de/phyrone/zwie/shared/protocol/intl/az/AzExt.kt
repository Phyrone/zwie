package de.phyrone.zwie.shared.protocol.intl.az

import de.phyrone.zwie.shared.crypt.gpg.GPG
import de.phyrone.zwie.shared.crypt.gpg.GPGKeyPriv
import de.phyrone.zwie.shared.crypt.gpg.GPGKeyPub
import de.phyrone.zwie.shared.protocol.init.PacketInit1
import de.phyrone.zwie.shared.protocol.init.PacketInit2
import io.ktor.util.Identity.encode
import io.ktor.websocket.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf


@OptIn(ExperimentalSerializationApi::class)
suspend fun WebSocketSession.runHandshakeClient(
    privateKey: GPGKeyPriv,
    alreadyEncrypted: Boolean,
): GPGKeyPub {
    val proto = ProtoBuf {
        this.encodeDefaults = false
    }

    send(
        proto.encodeToByteArray(
            PacketInit1(
                PROTCOL_VERSION,
                privateKey.getPublicKey().toByteArray(),
                alreadyEncrypted
            )
        )
    )
    val packetInit2Bytes = GPG.decrypt(
        (incoming.receive() as? Frame.Binary)?.readBytes()
            ?: error("only binary is supported"), privateKey
    )
    val (serverVersion, serverPubKey, challenge) = proto.decodeFromByteArray<PacketInit2>(packetInit2Bytes)
    GPGKeyPub

    TODO()
}

suspend fun WebSocketSession.runHandshakeServer(
    privateKey: GPGKeyPriv
): GPGKeyPub {

    TODO()
}

private const val PROTCOL_VERSION: ULong = 1UL