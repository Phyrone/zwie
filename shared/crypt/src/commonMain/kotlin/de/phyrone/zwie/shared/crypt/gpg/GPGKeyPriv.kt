package de.phyrone.zwie.shared.crypt.gpg

expect class GPGKeyPriv {
    fun getPublicKey(): GPGKeyPub

    suspend fun toAsciiArmor(): String
    suspend fun toByteArray(): ByteArray

    companion object {
        suspend fun fromByteArray(byteArray: ByteArray): GPGKeyPriv

        suspend fun fromAsciiArmor(armored: String): GPGKeyPriv
    }
}