package de.phyrone.zwie.shared.crypt

expect object MultiplatformSecureRandom {
    fun nextByteArray(size: Int): ByteArray
}