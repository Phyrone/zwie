package de.phyrone.zwie.shared.crypt.gpg

expect class GPGKeyPub {

    fun toAsciiArmored(): String
    fun toByteArray(): ByteArray

    companion object {
        suspend fun fromByteArray(byteArray: ByteArray): GPGKeyPub

        suspend fun fromAsciiArmor(armored: String): GPGKeyPub
    }
}