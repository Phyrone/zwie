package de.phyrone.zwie.shared.protocol.intl.az

import de.phyrone.zwie.shared.protocol.intl.SizeDescriptor

sealed class PacketHeader(
    private val type: PacketType,
    val secure: Boolean = true
) {

    fun toByte(): Byte =
        ((type.id and TYPE_PART_MASK) or ((encodeFlags().toInt() shl FLAG_PART_SHIFT) and FLAG_PART_MASK)).toByte()

    abstract fun encodeFlags(): Byte

    object Ping : PacketHeader(PacketType.PING, false) {
        override fun encodeFlags(): Byte = 0
    }

    object Pong : PacketHeader(PacketType.PONG, false) {
        override fun encodeFlags(): Byte = 0
    }

    object Control : PacketHeader(PacketType.CONTROL) {
        override fun encodeFlags(): Byte = 0
    }

    data class Channel(
        val idSize: SizeDescriptor,
    ) : PacketHeader(PacketType.CHANNEL) {

        constructor(byte: Byte) : this(SizeDescriptor.fromBits(byte.toUByte()))

        override fun encodeFlags(): Byte = idSize.toByte().toByte()

    }


    companion object {
        private const val TYPE_PART_MASK = 0x0F

        private const val FLAG_PART_MASK = 0xF0
        private const val FLAG_PART_SHIFT = 4


        fun fromByte(byte: Byte): PacketHeader {
            val typePart = byte.toInt() and 0x0F
            val flagPart = (byte.toInt() and 0xF0 shr FLAG_PART_SHIFT).toByte()
            return when (PacketType.fromByte(typePart)) {
                PacketType.PING -> Ping
                PacketType.PONG -> Pong
                PacketType.CALL -> TODO()
                PacketType.CALL_RESPONSE -> TODO()
                PacketType.CALL_ERROR -> TODO()
                PacketType.CHANNEL -> Channel(flagPart)
                PacketType.EVENT -> TODO()
                PacketType.CONTROL -> Control
            }
        }
    }

}