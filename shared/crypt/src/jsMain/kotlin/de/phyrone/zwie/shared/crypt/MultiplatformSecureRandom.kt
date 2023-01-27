package de.phyrone.zwie.shared.crypt

import de.phyrone.zwie.shared.crypt.import.common.SecureRandomJS


actual object MultiplatformSecureRandom {
    actual fun nextByteArray(size: Int): ByteArray = SecureRandomJS.randomArray(size)

}