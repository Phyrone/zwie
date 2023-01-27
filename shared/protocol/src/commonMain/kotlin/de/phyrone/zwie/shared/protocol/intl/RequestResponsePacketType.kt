package de.phyrone.zwie.shared.protocol.intl

import kotlin.experimental.and

sealed class RequestResponsePacketType(
    val type: Type
) {

    fun toByte() = toUByte().toByte()
    fun toUByte(): UByte = (type.id.toUByte() and encodeFlags())
    abstract fun encodeFlags(): UByte

    enum class Type(
        val id: Byte
    ) {
        CONTROL_CALL(0x01),
        RPC_CALL(0x02),
    }

    class ControlCall : RequestResponsePacketType(Type.CONTROL_CALL) {
        override fun encodeFlags(): UByte = 0u
    }

    data class RPCCall(
        val requestTypeSize: SizeDescriptor
    ) : RequestResponsePacketType(Type.RPC_CALL) {
        override fun encodeFlags(): UByte = requestTypeSize.toByte()

    }


    companion object {
        fun fromByte(byte: Byte) = fromUByte(byte.toUByte())
        fun fromUByte(uByte: UByte): RequestResponsePacketType {
            val typePart = (uByte.toUInt() and 0x0Fu).toByte()
            val flagsPart = uByte.toUInt() shr 4
            return when (typePart) {
                Type.CONTROL_CALL.id -> ControlCall()
                Type.RPC_CALL.id -> RPCCall(SizeDescriptor.fromBits(flagsPart.toUByte()))
                else -> throw IllegalArgumentException("unknown type: $typePart")
            }
        }
    }
}