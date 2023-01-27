package de.phyrone.zwie.shared.crypt

import java.security.SecureRandom
import java.security.SecureRandomParameters

actual object MultiplatformSecureRandom {

    private val secureRandom by lazy { SecureRandom.getInstanceStrong() }
    actual fun nextByteArray(size: Int): ByteArray = ByteArray(size).also { secureRandom.nextBytes(it) }
}