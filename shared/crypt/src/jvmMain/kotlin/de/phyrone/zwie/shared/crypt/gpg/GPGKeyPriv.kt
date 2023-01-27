package de.phyrone.zwie.shared.crypt.gpg

import org.bouncycastle.openpgp.PGPPublicKeyRing
import org.bouncycastle.openpgp.PGPSecretKeyRing
import org.pgpainless.PGPainless

actual class GPGKeyPriv(
     val pgpSecretKeyRing: PGPSecretKeyRing,
) {
    actual fun getPublicKey(): GPGKeyPub =
        GPGKeyPub(PGPPublicKeyRing(pgpSecretKeyRing.publicKeys.asSequence().toList()))

    actual suspend fun toAsciiArmor(): String = PGPainless.asciiArmor(pgpSecretKeyRing)

    actual suspend fun toByteArray(): ByteArray {
        PGPainless.modifyKeyRing(pgpSecretKeyRing)
        return pgpSecretKeyRing.encoded
    }

    actual companion object {
        actual suspend fun fromByteArray(byteArray: ByteArray): GPGKeyPriv {
            val keyRing = PGPainless.readKeyRing()
                .secretKeyRing(byteArray)

            return GPGKeyPriv(keyRing)
        }

        actual suspend fun fromAsciiArmor(armored: String): GPGKeyPriv {
            val keyRing = PGPainless.readKeyRing().secretKeyRing(armored)
            return GPGKeyPriv(keyRing)
        }
    }

}