package de.phyrone.zwie.shared.crypt.gpg

import global.GPGJS.KeyPair
import global.GPGJS.PrivateKey
import global.GPGJS.`T$1`
import global.GPGJS.`T$2`
import kotlinx.coroutines.await
import org.khronos.webgl.Uint8Array
import global.GPGJS.readPrivateKey as gpgJsReadPrivateKey

actual class GPGKeyPriv(
    val actualPrivateKey: PrivateKey,
) {
    actual fun getPublicKey(): GPGKeyPub = GPGKeyPub(actualPrivateKey.toPublic())
    actual suspend fun toAsciiArmor(): String = actualPrivateKey.armor()
    actual suspend fun toByteArray(): ByteArray = actualPrivateKey.write().unsafeCast<ByteArray>()

    actual companion object {
        actual suspend fun fromByteArray(byteArray: ByteArray): GPGKeyPriv {
            val privKey = gpgJsReadPrivateKey(object : `T$2` {
                override var binaryKey: Uint8Array = byteArray.unsafeCast<Uint8Array>()
            }).await()
            return GPGKeyPriv(privKey)
        }

        actual suspend fun fromAsciiArmor(armored: String): GPGKeyPriv {
            val privKey = gpgJsReadPrivateKey(object : `T$1` {
                override var armoredKey: String = armored
            }).await()
            return GPGKeyPriv(privKey)
        }

    }
}