package de.phyrone.zwie.shared.protocol.intl.az

import de.phyrone.zwie.shared.crypt.MultiplatformSecureRandom
import de.phyrone.zwie.shared.crypt.gpg.GPG
import de.phyrone.zwie.shared.crypt.gpg.GPGKeyPriv
import de.phyrone.zwie.shared.crypt.gpg.GPGKeyPub
import de.phyrone.zwie.shared.protocol.init.PacketInit1
import de.phyrone.zwie.shared.protocol.init.PacketInit2
import de.phyrone.zwie.shared.protocol.init.PacketInit3
import io.ktor.websocket.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray


@OptIn(ExperimentalSerializationApi::class)
suspend fun WebSocketSession.runHandshakeClient(
    privateKey: GPGKeyPriv,
    alreadyEncrypted: Boolean,
): ClientHandshakeResult {


    val clientPubKey = privateKey.getPublicKey()
    println("clientpubkey: $clientPubKey")
    val clientPubKeyBytes = clientPubKey.toByteArray()
    println("clientpubkeybytes: $clientPubKeyBytes")

    val init1 = PacketInit1(
        PROTCOL_VERSION, clientPubKeyBytes, alreadyEncrypted
    )
    println("packet init1=${init1}")
    val init1Bytes = init1.toByteArray()
    println("packet init1 bytes=${init1Bytes}")

    println("sending init1")
    send(init1Bytes)

    val packetInit2Bytes = GPG.decrypt(
        (incoming.receive() as? Frame.Binary)?.readBytes() ?: error("only binary is supported"), privateKey
    )
    val (serverVersion, serverKeyBytes, challenge) = PacketInit2.fromByteArray(packetInit2Bytes)
    val serverKey = GPGKeyPub.fromByteArray(serverKeyBytes)

    val packetInit3 = PacketInit3(challenge.reversedArray())
    val packetInit3Bytes = packetInit3.toByteArray()
    val encryptedPacketInit3Bytes = GPG.signAndEncrypt(packetInit3Bytes, serverKey, privateKey)
    send(encryptedPacketInit3Bytes)

    return ClientHandshakeResult(serverVersion, serverKey)
}

data class ClientHandshakeResult(
    val serverVersion: Int,
    val serverPubKey: GPGKeyPub,
)

suspend fun WebSocketSession.runHandshakeServer(
    privateKey: GPGKeyPriv
): ServerHandshakeResult {

    val packetInit1Bytes = ((incoming.receive() as? Frame.Binary)?.readBytes() ?: error("only binary is supported"))
    val (clientVersion, clientKeyBytes, alreadyEncrypted) = PacketInit1.fromByteArray(packetInit1Bytes)
    val packetInit2 = PacketInit2(
        PROTCOL_VERSION, privateKey.getPublicKey().toByteArray(), MultiplatformSecureRandom.nextByteArray(256)
    )
    val packetInit2Bytes = packetInit2.toByteArray()
    val clientPubKey = GPGKeyPub.fromByteArray(clientKeyBytes)
    val encryptedPacketInit2Bytes = GPG.encrypt(packetInit2Bytes, clientPubKey)
    send(encryptedPacketInit2Bytes)

    val packetInit3BytesEncrypted =
        (incoming.receive() as? Frame.Binary)?.readBytes() ?: error("only binary is supported")
    val packetInit3Bytes = GPG.decryptAndVerify(packetInit3BytesEncrypted, privateKey, clientPubKey)
    val (response) = PacketInit3.fromByteArray(packetInit3Bytes)
    if (!response.contentEquals(packetInit2.challenge.reversedArray()))
        error("invalid response")

    return ServerHandshakeResult(clientVersion, clientPubKey, alreadyEncrypted)
}

data class ServerHandshakeResult(
    val clientVersion: Int,
    val clientPubKey: GPGKeyPub,
    val alreadyEncrypted: Boolean,
)

private const val PROTCOL_VERSION: Int = 1