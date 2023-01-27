package de.phyrone.zwie.shared.protocol.control

import kotlinx.serialization.Serializable

@Serializable
enum class ControlPacketType(val id: UShort) {

    /* Call Control (1X) */


    /* Channel Control (2X) */
    CHANNEL_OPEN_REQUEST(0x20u),
    CHANNEl_OPEN_ACCEPTED(0x21u),
    CHANNEL_OPEN_REJECTED(0x22u),

    CHANNEL_CLOSE(0x2Fu),

    /* Socket control (FX)*/
    /**
     * i32 iteration
     */

    READY(0xF1u),
    CLOSE(0xFFu),

    /* Error (EX) */
    ERROR(0xE1u),

    /* Debug (DX) */
    DEBUG_LOG(0xD1u),


    ;

    fun toByte(): Byte = id.toByte()

    companion object {
        private val fromIdMap = values().associateBy { it.id }
        fun fromID(short: UShort) = fromIdMap[short]
    }
}