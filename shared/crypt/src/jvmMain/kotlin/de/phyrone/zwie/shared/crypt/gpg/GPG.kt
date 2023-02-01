package de.phyrone.zwie.shared.crypt.gpg

import de.phyrone.zwie.shared.crypt.gpg.exception.GPGVerficationException
import org.pgpainless.PGPainless
import org.pgpainless.algorithm.DocumentSignatureType
import org.pgpainless.algorithm.EncryptionPurpose
import org.pgpainless.decryption_verification.ConsumerOptions
import org.pgpainless.decryption_verification.MessageMetadata
import org.pgpainless.encryption_signing.EncryptionOptions
import org.pgpainless.encryption_signing.ProducerOptions
import org.pgpainless.encryption_signing.SigningOptions
import org.pgpainless.key.protection.SecretKeyRingProtector
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

actual object GPG {

    actual suspend fun generateKey(): GPGKeyPriv {
        val keyring = PGPainless.generateKeyRing().modernKeyRing(null)

        return GPGKeyPriv(keyring)
    }

    private fun ByteArray.produce(options: ProducerOptions) = ByteArrayOutputStream()
        .use { byteArrayOutputStream ->
            val encryptionStream =
                PGPainless.encryptAndOrSign().onOutputStream(byteArrayOutputStream).withOptions(options)
            encryptionStream.use { secureStream ->
                secureStream.write(this)
                secureStream.flush()
            }
            val result = encryptionStream.result
            byteArrayOutputStream.toByteArray() to result
        }

    private fun ByteArray.consume(options: ConsumerOptions) = ByteArrayInputStream(this)
        .use { readStream ->
            PGPainless.decryptAndOrVerify().onInputStream(readStream).withOptions(options).use { dataStream ->

                val data = dataStream.readBytes()
                val metadata = dataStream.metadata
                Pair(data, metadata)
            }
        }

    actual suspend fun createSignature(data: ByteArray, key: GPGKeyPriv): ByteArray = data.produce(
        ProducerOptions.sign(
            SigningOptions.get().addDetachedSignature(
                SecretKeyRingProtector.unprotectedKeys(),
                key.pgpSecretKeyRing,
                DocumentSignatureType.BINARY_DOCUMENT
            )
        ).setAsciiArmor(false).setHideArmorHeaders(true)
    ).second.detachedSignatures.let { it.get(it.keySet().first()).first().encoded }

    private fun Pair<ByteArray, MessageMetadata>.requireVerifiedSignedBy(key: GPGKeyPub): Pair<ByteArray, MessageMetadata> {
        if (!second.isVerifiedSignedBy(key.pgpPublicKeyRing))
            throw GPGVerficationException("Signature is not valid for this data")
        return this
    }

    private fun Pair<ByteArray, MessageMetadata>.requireEncryptedFor(key: GPGKeyPriv): Pair<ByteArray, MessageMetadata> {
        if (!second.isEncryptedFor(key.pgpSecretKeyRing))
            throw GPGVerficationException("Signature is not valid for this data")
        return this
    }

    actual suspend fun verifySignature(
        data: ByteArray, signature: ByteArray, key: GPGKeyPub
    ): Unit = ByteArrayInputStream(signature).use { signatureStream ->
        data.consume(
            ConsumerOptions()
                .addVerificationCert(key.pgpPublicKeyRing)
                .addVerificationOfDetachedSignatures(signatureStream)
        ).requireVerifiedSignedBy(key).first
    }

    actual suspend fun encrypt(data: ByteArray, key: GPGKeyPub): ByteArray = data.produce(
        ProducerOptions.encrypt(
            EncryptionOptions(EncryptionPurpose.COMMUNICATIONS)
                .addRecipient(key.pgpPublicKeyRing)
        ).setAsciiArmor(false).setHideArmorHeaders(true)
    ).first


    actual suspend fun decrypt(data: ByteArray, key: GPGKeyPriv): ByteArray =
        data.consume(
            ConsumerOptions()
                .addDecryptionKey(key.pgpSecretKeyRing, SecretKeyRingProtector.unprotectedKeys())
        ).requireEncryptedFor(key).first

    actual suspend fun signAndEncrypt(
        data: ByteArray,
        encryptKey: GPGKeyPub,
        singKey: GPGKeyPriv
    ): ByteArray = data.produce(
        ProducerOptions.signAndEncrypt(
            EncryptionOptions(EncryptionPurpose.COMMUNICATIONS)
                .addRecipient(encryptKey.pgpPublicKeyRing),
            SigningOptions().addSignature(SecretKeyRingProtector.unprotectedKeys(), singKey.pgpSecretKeyRing)
        ).setAsciiArmor(false).setHideArmorHeaders(true)
    ).first


    actual suspend fun decryptAndVerify(
        data: ByteArray,
        decryptKey: GPGKeyPriv,
        verifyKey: GPGKeyPub
    ): ByteArray = data.consume(ConsumerOptions())
        .requireVerifiedSignedBy(verifyKey)
        .requireEncryptedFor(decryptKey)
        .first

    actual suspend fun signInline(
        data: ByteArray,
        key: GPGKeyPriv
    ): ByteArray = data.produce(
        ProducerOptions.sign(
            SigningOptions()
                .addSignature(SecretKeyRingProtector.unprotectedKeys(), key.pgpSecretKeyRing)
        ).setAsciiArmor(false).setHideArmorHeaders(true)
    ).first

    actual suspend fun verifyInline(
        data: ByteArray,
        key: GPGKeyPub
    ): ByteArray = data.consume(
        ConsumerOptions()
            .addVerificationCert(key.pgpPublicKeyRing)
    ).requireVerifiedSignedBy(key)
        .first


}

