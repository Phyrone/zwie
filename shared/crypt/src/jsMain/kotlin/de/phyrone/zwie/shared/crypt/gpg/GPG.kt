package de.phyrone.zwie.shared.crypt.gpg

import de.phyrone.zwie.shared.crypt.gpg.exception.GPGVerficationException
import de.phyrone.zwie.shared.crypt.utils.toUint8Array
import global.GPGJS.*
import kotlinx.coroutines.await
import org.khronos.webgl.Int8Array
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
                format: "object",
                userIDs: [{name: "t", email: "t@t.t"}],
            }
            """
        )
        val key =
            (gpgJsGenerateKey(generateOptions.unsafeCast<GenerateKeyOptions>()) as Promise<KeyPair>).await().privateKey
        return GPGKeyPriv(key)
    }

    private suspend fun ByteArray.createMessage(): Message<ByteArray> {
        console.log("createMessage", this)
        return gpgJsCreateMessage(
            object {
                @JsName("binary")
                val binary: ByteArray = this@createMessage.toUint8Array().unsafeCast<ByteArray>()
            }.unsafeCast<`T$36`<ByteArray>>(),
        ).await()
    }

    private suspend fun ByteArray.readMessage(): Message<ByteArray> {

        @Suppress("UNUSED_VARIABLE") val messageBytes = Uint8Array(this.toTypedArray())
        val options = js("""{binaryMessage: messageBytes}""")
        //console.log("options", options)
        return gpgJsReadMessage(options.unsafeCast<`T$34`<ByteArray>>()).await()
        /*return gpgJsReadMessage(
            object : `T$34`<ByteArray> {
                override var binaryMessage: ByteArray =
                    Uint8Array(this@readMessage.toTypedArray()).unsafeCast<ByteArray>()
            },
        ).await()*/
    }


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
        @Suppress("UNUSED_VARIABLE") val message = data.readMessage()
        @Suppress("UNUSED_VARIABLE") val pkey = key.actualPrivateKey
        val options = object {
            @JsName("message")
            val message = message

            @JsName("decryptionKeys")
            val decryptionKeys = pkey

            @JsName("format")
            val format = "binary"
        }
        val decryptMessageResult = gpgJsDecrypt<ByteArray>(options.unsafeCast<DecryptOptions>()).await()
        //if (decryptMessageResult.signatures.any { !it.verified.await() })
        //    throw GPGVerficationException("Signature not valid")
        js("""console.log("decryptMessageResult", decryptMessageResult)""")

        val resultDataU = (decryptMessageResult.data as Uint8Array)
        val resultData = Int8Array(resultDataU.buffer)


        return resultData.unsafeCast<ByteArray>()
        //return resultData
    }

    actual suspend fun signAndEncrypt(
        data: ByteArray,
        encryptKey: GPGKeyPub,
        singKey: GPGKeyPriv
    ): ByteArray {
        val message = data.createMessage()
        return (gpgJsEncrypt<Message<ByteArray>>(object {
            @JsName("message")
            var message: Message<dynamic> = message

            @JsName("format")
            var format: String? = "binary"

            @JsName("encryptionKeys")
            var encryptionKeys: dynamic = encryptKey.actualPublicKey

            @JsName("signingKeys")
            var signingKeys: dynamic = singKey.actualPrivateKey
        }.unsafeCast<EncryptOptions>()) as Promise<ByteArray>).await()
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