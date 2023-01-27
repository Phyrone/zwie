package de.phyrone.zwie.shared.protocol.intl

sealed class FireAndForgetPacketType(
    val type: Type
) {

    abstract val secure: Boolean

    fun toUByte(): UByte = (type.id + (encodeFlags().toUInt() shl FLAG_OFFSET)).toUByte()
    fun toByte() = toUByte().toByte()

    abstract fun encodeFlags(): UByte

    enum class Type(val id: UByte) {
        PING_PONG(0x00u),
        CONTROL_CALL(0x01u),
        RPC_CALL(0x02u);

        init {
            require(id <= PACKET_TYPE_MASK) { "id must be at most $PACKET_TYPE_MASK" }
        }
    }

    //no flags yet
    data class PingPong(val pong: Boolean) : FireAndForgetPacketType(Type.PING_PONG) {
        override val secure: Boolean = false

        override fun encodeFlags(): UByte = if (pong) 0x01u else 0x00u

        companion object {
            fun fromByte(byte: UByte): PingPong {
                return PingPong(byte.toUInt() and 0x01u == 0x01u)
            }
        }
    }


    class Control : FireAndForgetPacketType(Type.CONTROL_CALL) {
        override val secure: Boolean = true
        override fun encodeFlags(): UByte = 0u

        companion object {
            fun fromByte(byte: UByte): Control = Control()
        }
    }

    data class RPCCall(
        val requestTypeSize: SizeDescriptor
    ) : FireAndForgetPacketType(Type.RPC_CALL) {
        override val secure: Boolean = true


        override fun encodeFlags(): UByte = requestTypeSize.toByte()

        companion object {
            fun fromByte(byte: UByte): RPCCall = RPCCall(SizeDescriptor.fromBits(byte))
        }
    }


    companion object {
        fun fromUByte(byte: UByte): FireAndForgetPacketType {
            val typePart = byte and PACKET_TYPE_MASK
            val flagPart = ((byte).toUInt() shr FLAG_OFFSET).toUByte()

            return when (Type.values().first { it.id == typePart }) {
                Type.PING_PONG -> PingPong.fromByte(flagPart)
                Type.CONTROL_CALL -> Control.fromByte(flagPart)
                Type.RPC_CALL -> RPCCall.fromByte(flagPart)
            }
        }
        fun fromByte(byte: Byte): FireAndForgetPacketType = fromUByte(byte.toUByte())


        private const val PACKET_TYPE_MASK: UByte = 0b00001111u
        private val PACKET_FLAG_MASK: UByte = PACKET_TYPE_MASK xor 0xFFu

        private val FLAG_OFFSET = PACKET_TYPE_MASK.takeHighestOneBit()
            .coerceAtLeast(1u)
            .countTrailingZeroBits() + 1
    }

}