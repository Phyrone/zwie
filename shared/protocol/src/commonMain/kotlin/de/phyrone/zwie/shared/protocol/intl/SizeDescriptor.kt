package de.phyrone.zwie.shared.protocol.intl

import io.ktor.utils.io.core.*

enum class SizeDescriptor(
    val bits: Int,
    val bytes: Int,
) {
    SIZE_8(UByte.SIZE_BITS, UByte.SIZE_BYTES),
    SIZE_16(UShort.SIZE_BITS, UShort.SIZE_BYTES),
    SIZE_32(UInt.SIZE_BITS, UInt.SIZE_BYTES),
    SIZE_64(ULong.SIZE_BITS, ULong.SIZE_BYTES);

    fun toByte(): UByte = when (this) {
        SIZE_8 -> 0b00u
        SIZE_16 -> DOUBLE_MASK
        SIZE_32 -> QUAD_MASK
        SIZE_64 -> DOUBLE_MASK or QUAD_MASK
    }

    fun fromPacket(packet: ByteReadPacket): ULong = when (this) {
        SIZE_8 -> packet.readUByte().toULong()
        SIZE_16 -> packet.readUShort().toULong()
        SIZE_32 -> packet.readUInt().toULong()
        SIZE_64 -> packet.readULong()
    }

    fun toPacket(value: ULong): ByteReadPacket {
        require(value <= 1uL shl (bits - 1)) { "Value is to big for this SizeDescriptor" }

        val packet = BytePacketBuilder()
        when (this) {
            SIZE_8 -> packet.writeUByte(value.toUByte())
            SIZE_16 -> packet.writeUShort(value.toUShort())
            SIZE_32 -> packet.writeUInt(value.toUInt())
            SIZE_64 -> packet.writeULong(value)
        }
        return packet.build()
    }


    companion object {

        /**
         * takes first 2 bits to determine the size
         */
        fun fromBits(byte: UByte): SizeDescriptor {
            //alternatively you can think this as an 2 bit integer
            val double = (byte and DOUBLE_MASK).toUInt() == 0b01u
            val quad = (byte and QUAD_MASK).toUInt() == 0b01u
            return when {
                double && quad -> SIZE_64
                quad -> SIZE_32
                double -> SIZE_16
                else -> SIZE_8
            }
        }

        private const val DOUBLE_MASK: UByte = 0b0001u
        private const val QUAD_MASK: UByte = 0b0010u
        const val BITS = 2
        fun fromSample(sample: ULong): SizeDescriptor {
            val zeroBits = sample.takeHighestOneBit()
                .coerceAtLeast(1u) //when sample is 0, countTrailingZeroBits() returns 0
                .countTrailingZeroBits() // +1 = highest bit
            return values().first { it.bits > zeroBits }
        }

    }
}