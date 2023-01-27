package de.phyrone.zwie.shared.crypt.aes

expect object AES {

    fun encrypt(data: ByteArray, key: ByteArray, iv: ByteArray): ByteArray

    fun decrypt(data: ByteArray, key: ByteArray, iv: ByteArray): ByteArray

}