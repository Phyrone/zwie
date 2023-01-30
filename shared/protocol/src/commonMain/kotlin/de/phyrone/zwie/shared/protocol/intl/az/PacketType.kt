package de.phyrone.zwie.shared.protocol.intl.az

enum class PacketType(
    val id: Int,
    val secure: Boolean = true,
) {

    HEARTBEAT(0x00, false),
    PING(0x01, false),
    PONG(0x02, false),

    CALL(0x05),
    CALL_RESPONSE(0x06),
    CALL_ERROR(0x07),

    CHANNEL(0x0A),
    EVENT(0x0B),

    CONTROL(0x0F);

    companion object {
        fun fromByte(byte: Int): PacketType {
            val filter = byte and 0x0F
            return values().first { it.id == filter }
        }
    }
}