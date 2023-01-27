package de.phyrone.zwie.shared.crypt.gpg


expect object GPG {

    suspend fun generateKey(): GPGKeyPriv


    suspend fun createSignature(data: ByteArray, key: GPGKeyPriv): ByteArray

    suspend fun verifySignature(data: ByteArray, signature: ByteArray, key: GPGKeyPub)

    suspend fun signInline(data: ByteArray, key: GPGKeyPriv): ByteArray

    suspend fun verifyInline(data: ByteArray, key: GPGKeyPub): ByteArray

    suspend fun encrypt(data: ByteArray, key: GPGKeyPub): ByteArray

    suspend fun decrypt(data: ByteArray, key: GPGKeyPriv): ByteArray

    suspend fun signAndEncrypt(data: ByteArray, encryptKey: GPGKeyPub, singKey: GPGKeyPriv): ByteArray

    suspend fun decryptAndVerify(data: ByteArray, decryptKey: GPGKeyPriv, verifyKey: GPGKeyPub): ByteArray

}