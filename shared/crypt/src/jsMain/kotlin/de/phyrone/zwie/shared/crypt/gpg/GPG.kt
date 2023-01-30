package de.phyrone.zwie.shared.crypt.gpg

import de.phyrone.zwie.shared.crypt.gpg.exception.GPGVerficationException
import global.GPGJS.*
import kotlinx.coroutines.await
import org.khronos.webgl.Uint8Array
import kotlin.js.Promise
import global.GPGJS.createMessage as gpgJsCreateMessage
import global.GPGJS.generateKey as gpgJsGenerateKey
import global.GPGJS.readMessage as gpgJsReadMessage
import global.GPGJS.encrypt as gpgJsEncrypt
import global.GPGJS.decrypt as gpgJsDecrypt
import global.GPGJS.sign as gpgJsSign
import global.GPGJS.verify as gpgJsVerify


actual object GPG {
    actual suspend fun generateKey(): GPGKeyPriv {

        val generateOptions = js(
            """{
                curve:"ed25519",
                format: "object"
            }
            """
        )
        val key = (gpgJsGenerateKey(generateOptions.unsafeCast<GenerateKeyOptions>()) as Promise<KeyPair>).await().privateKey
        return GPGKeyPriv(key)
    }

    private suspend fun ByteArray.createMessage() = gpgJsCreateMessage(
        object : `T$36`<ByteArray> {
            override var binary: ByteArray = this@createMessage
        },
    ).await()

    private suspend fun ByteArray.readMessage() = gpgJsReadMessage(
        object : `T$34`<ByteArray> {
            override var binaryMessage: ByteArray = this@readMessage
        },
    ).await()


    actual suspend fun createSignature(data: ByteArray, key: GPGKeyPriv): ByteArray {
        val message = data.createMessage()
        return (gpgJsSign(object : SignOptions {
            override var format: String? = "binary"
            override var message = message
            override var signingKeys: dynamic = key.actualPrivateKey
            override var detached: Boolean? = true
        }) as Promise<ByteArray>).await()
    }

    actual suspend fun verifySignature(data: ByteArray, signature: ByteArray, key: GPGKeyPub) {
        val message = data.readMessage()

        val readSignature = readSignature(object : `T$28` {
            override var binarySignature: Uint8Array = signature.unsafeCast<Uint8Array>()
        }).await()

        val result = gpgJsVerify<ByteArray>(object : VerifyOptions {
            override var message: dynamic = message
            override var signature: dynamic = readSignature
            override var verificationKeys: dynamic = key.actualPublicKey
        }).await()
        if (result.signatures.any { !it.verified.await() })
            throw GPGVerficationException("Signature not valid")
    }

    actual suspend fun encrypt(
        data: ByteArray,
        key: GPGKeyPub
    ): ByteArray {

        val message = data.createMessage()
        return (gpgJsEncrypt<Message<ByteArray>>(object : EncryptOptions {
            override var message: Message<dynamic> = message
            override var format: String? = "binary"
            override var encryptionKeys: dynamic = key.actualPublicKey
        }) as Promise<ByteArray>).await()
    }

    actual suspend fun decrypt(
        data: ByteArray,
        key: GPGKeyPriv
    ): ByteArray {
        val message = data.readMessage()
        val encrypted = gpgJsDecrypt<ByteArray>(object : DecryptOptions {
            override var message: Message<dynamic> = message

        }).await()
        if (encrypted.signatures.any { !it.verified.await() })
            throw GPGVerficationException("Signature not valid")

        return encrypted.data as ByteArray
    }

    actual suspend fun signAndEncrypt(
        data: ByteArray,
        encryptKey: GPGKeyPub,
        singKey: GPGKeyPriv
    ): ByteArray {
        val message = data.createMessage()
        return (gpgJsEncrypt<Message<ByteArray>>(object : EncryptOptions {
            override var message: Message<dynamic> = message
            override var format: String? = "binary"
            override var encryptionKeys: dynamic = encryptKey.actualPublicKey
            override var signingKeys: dynamic = singKey.actualPrivateKey
        }) as Promise<ByteArray>).await()
    }

    actual suspend fun decryptAndVerify(
        data: ByteArray,
        decryptKey: GPGKeyPriv,
        verifyKey: GPGKeyPub
    ): ByteArray {
        val message = data.readMessage()
        val result = gpgJsDecrypt<ByteArray>(object : DecryptOptions {
            override var message: Message<dynamic> = message
            override var format: String? = "binary"
            override var decryptionKeys: dynamic = decryptKey.actualPrivateKey
            override var verificationKeys: dynamic = verifyKey.actualPublicKey
        }).await()
        if (result.signatures.any { !it.verified.await() })
            return result.data as ByteArray
        else throw GPGVerficationException("Signature not verified")
    }

    actual suspend fun signInline(
        data: ByteArray,
        key: GPGKeyPriv
    ): ByteArray {
        val message = data.createMessage()
        return (gpgJsSign<ByteArray>(object : SignOptions {
            override var message: dynamic = message
            override var signingKeys: dynamic = key.actualPrivateKey
            override var format: String? = "binary"
        }) as Promise<ByteArray>).await()
    }

    actual suspend fun verifyInline(
        data: ByteArray,
        key: GPGKeyPub
    ): ByteArray {
        val message = data.readMessage()
        val result = gpgJsVerify<ByteArray>(object : VerifyOptions {
            override var message: dynamic = message
            override var verificationKeys: dynamic = key.actualPublicKey
        }).await()
        if (result.signatures.any { !it.verified.await() })
            throw GPGVerficationException("Signature not valid")

        return result.data as ByteArray
    }


}