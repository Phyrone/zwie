package de.phyrone.zwie.shared.protocol

import kotlin.experimental.and
import kotlin.experimental.or
import kotlin.jvm.JvmInline

@JvmInline
value class ZFlagByte(
    private val byte: Byte = 0,
) {
    constructor(vararg flags: Flag) : this(byteFrom(*flags))

    operator fun get(flag: Flag): Boolean {
        return byte and flag.byte != 0.toByte()
    }


    enum class Flag(bitPosition: Int) {
        EXTENSION(0),
        SIGN_OR_ENCRYPT(1);

        val byte: Byte = 1.shl(bitPosition).toByte()
    }

    companion object {
        private fun byteFrom(vararg flags: Flag): Byte {
            var byte = 0.toByte()
            flags.forEach {
                byte = byte or it.byte
            }
            return byte
        }

        fun from(vararg flags: Flag): ZFlagByte {
            var byte = 0.toByte()
            flags.forEach {
                byte = byte or it.byte
            }
            return ZFlagByte(byte)
        }
    }
}