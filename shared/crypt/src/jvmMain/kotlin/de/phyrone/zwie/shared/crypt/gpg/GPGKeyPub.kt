package de.phyrone.zwie.shared.crypt.gpg

import org.bouncycastle.openpgp.PGPPublicKeyRing
import org.pgpainless.PGPainless

actual class GPGKeyPub(
    val pgpPublicKeyRing: PGPPublicKeyRing,
) {

    actual fun toByteArray(): ByteArray = pgpPublicKeyRing.encoded

    actual fun toAsciiArmored(): String = PGPainless.asciiArmor(pgpPublicKeyRing)

    actual companion object {
        actual suspend fun fromByteArray(byteArray: ByteArray): GPGKeyPub {
            val keyRing = PGPainless.readKeyRing().publicKeyRing(byteArray)
            return GPGKeyPub(keyRing)
        }

        actual suspend fun fromAsciiArmor(armored: String): GPGKeyPub {
            val keyRing = PGPainless.readKeyRing().publicKeyRing(armored)
            return GPGKeyPub(keyRing)
        }
    }


}
