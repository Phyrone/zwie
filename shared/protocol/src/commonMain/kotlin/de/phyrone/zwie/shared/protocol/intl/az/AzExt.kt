package de.phyrone.zwie.shared.protocol.intl.az

import de.phyrone.zwie.shared.crypt.MultiplatformSecureRandom
import de.phyrone.zwie.shared.crypt.gpg.GPG
import de.phyrone.zwie.shared.crypt.gpg.GPGKeyPriv
import de.phyrone.zwie.shared.crypt.gpg.GPGKeyPub
import de.phyrone.zwie.shared.protocol.init.PacketInit1
import de.phyrone.zwie.shared.protocol.init.PacketInit2
import de.phyrone.zwie.shared.protocol.init.PacketInit3
import io.ktor.util.Identity.encode
import io.ktor.websocket.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import kotlin.random.Random


@OptIn(ExperimentalSerializationApi::class)
suspend fun WebSocketSession.runHandshakeClient(
    privateKey: GPGKeyPriv,
    alreadyEncrypted: Boolean,
): ClientHandshakeResult {
    val proto = ProtoBuf {
        this.encodeDefaults = false
    }

    send(
        proto.encodeToByteArray(
            PacketInit1(
                PROTCOL_VERSION, privateKey.getPublicKey().toByteArray(), alreadyEncrypted
            )
        )
    )
    val packetInit2Bytes = GPG.decrypt(
        (incoming.receive() as? Frame.Binary)?.readBytes() ?: error("only binary is supported"), privateKey
    )
    val (serverVersion, serverKeyBytes, challenge) = proto.decodeFromByteArray<PacketInit2>(packetInit2Bytes)
    val serverKey = GPGKeyPub.fromByteArray(serverKeyBytes)

    val packetInit3 = PacketInit3(challenge.reversedArray())
    val packetInit3Bytes = proto.encodeToByteArray(packetInit3)
    val encryptedPacketInit3Bytes = GPG.signAndEncrypt(packetInit3Bytes, serverKey, privateKey)
    send(encryptedPacketInit3Bytes)

    return ClientHandshakeResult(serverVersion, serverKey)
}

data class ClientHandshakeResult(
    val serverVersion: ULong,
    val serverPubKey: GPGKeyPub,
)

suspend fun WebSocketSession.runHandshakeServer(
    privateKey: GPGKeyPriv
): ServerHandshakeResult {
    val proto = ProtoBuf {
        this.encodeDefaults = false
    }
    val packetInit1Bytes = ((incoming.receive() as? Frame.Binary)?.readBytes() ?: error("only binary is supported"))
    val (clientVersion, clientKeyBytes, alreadyEncrypted) = proto.decodeFromByteArray<PacketInit1>(packetInit1Bytes)
    val packetInit2 = PacketInit2(
        PROTCOL_VERSION, privateKey.getPublicKey().toByteArray(), MultiplatformSecureRandom.nextByteArray(256)
    )
    val packetInit2Bytes = proto.encodeToByteArray(packetInit2)
    val clientPubKey = GPGKeyPub.fromByteArray(clientKeyBytes)
    val encryptedPacketInit2Bytes = GPG.encrypt(packetInit2Bytes, clientPubKey)
    send(encryptedPacketInit2Bytes)

    val packetInit3BytesEncrypted =
        (incoming.receive() as? Frame.Binary)?.readBytes() ?: error("only binary is supported")
    val packetInit3Bytes = GPG.decryptAndVerify(packetInit3BytesEncrypted, privateKey, clientPubKey)
    val (response) = proto.decodeFromByteArray<PacketInit3>(packetInit3Bytes)
    if (!response.contentEquals(packetInit2.challenge.reversedArray()))
        error("invalid response")

    return ServerHandshakeResult(clientVersion, clientPubKey, alreadyEncrypted)
}

data class ServerHandshakeResult(
    val clientVersion: ULong,
    val clientPubKey: GPGKeyPub,
    val alreadyEncrypted: Boolean,
)

private const val PROTCOL_VERSION: ULong = 1UL