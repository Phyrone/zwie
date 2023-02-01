package de.phyrone.zwie.shared.crypt.gpg

import de.phyrone.zwie.shared.crypt.utils.toUint8Array
import global.GPGJS.*
import kotlinx.coroutines.await
import org.khronos.webgl.Uint8Array

actual class GPGKeyPub(
    val actualPublicKey: PublicKey,
) {
    actual fun toAsciiArmored(): String = actualPublicKey.armor()
    actual fun toByteArray(): ByteArray = actualPublicKey.write().unsafeCast<ByteArray>()

    actual companion object {
        actual suspend fun fromByteArray(byteArray: ByteArray): GPGKeyPub {
            val pubKey = readKey(object {
                @JsName("binaryKey")
                val binaryKey: Uint8Array = byteArray.toUint8Array()
            }.unsafeCast<`T$2`>()).await() as PublicKey
            return GPGKeyPub(pubKey)
        }

        actual suspend fun fromAsciiArmor(armored: String): GPGKeyPub {
            val pubKey = readKey(object : `T$1` {
                override var armoredKey: String = armored
            }).await() as PublicKey
            return GPGKeyPub(pubKey)
        }
    }
}