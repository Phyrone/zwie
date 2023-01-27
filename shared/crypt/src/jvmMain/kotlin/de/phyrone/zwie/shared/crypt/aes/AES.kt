package de.phyrone.zwie.shared.crypt.aes

import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.Security
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

actual object AES {
    init {
        if (!Security.getProviders().any { it is BouncyCastleProvider })
            Security.addProvider(BouncyCastleProvider())
    }

    private const val AES_ALGORITHM = "AES/CBC/PKCS5Padding"
    private const val AES_TRANSFORMATION = "AES"
    private const val BOUNCY_CASTLE_PROVIDER = "BC"
    actual fun encrypt(data: ByteArray, key: ByteArray, iv: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(AES_ALGORITHM, BOUNCY_CASTLE_PROVIDER)
        val secretKey = SecretKeySpec(key, AES_TRANSFORMATION)
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec)
        return cipher.doFinal(data)
    }

    actual fun decrypt(data: ByteArray, key: ByteArray, iv: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(AES_ALGORITHM, BOUNCY_CASTLE_PROVIDER)
        val secretKey = SecretKeySpec(key, AES_TRANSFORMATION)
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec)
        return cipher.doFinal(data)
    }


}