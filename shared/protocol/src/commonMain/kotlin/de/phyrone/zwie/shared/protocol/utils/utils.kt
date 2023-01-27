package de.phyrone.zwie.shared.protocol.utils

import io.ktor.utils.io.core.*
import io.rsocket.kotlin.payload.Payload

fun ByteReadPacket.readVarInt(): ULong {
    var result = 0uL
    var shift = 0
    var fragment: Byte
    do {
        fragment = readByte()
        result = result or ((fragment.toUInt() and 0x7Fu).toULong() shl shift)
        shift += 7
    } while (fragment.toInt() and 0x80 != 0 && shift < 64)
    return result
}

fun ULong.toVarIntBinary(): ByteArray {
    var value = this
    val result = mutableListOf<Byte>()
    do {
        var fragment = (value and 0x7Fu)
        value = value shr 7
        if (value != 0uL) {
            fragment = fragment or 0x80u
        }
        result.add(fragment.toByte())
    } while (value != 0uL)
    return result.toByteArray()
}
fun BytePacketBuilder.writeVarInt(value: ULong) {
    var v = value
    do {
        var fragment = (v and 0x7Fu)
        v = v shr 7
        if (v != 0uL) {
            fragment = fragment or 0x80u
        }
        writeByte(fragment.toByte())
    } while (v != 0uL)
}


fun ULong.toLongZigZag(): Long = (this shl 1).toLong() xor (this shr 63).toLong()
fun Long.toULongZigZag(): ULong = ((this shr 1) xor (-(this and 1))).toULong()

operator fun Payload.component1(): ByteReadPacket = data
operator fun Payload.component2(): ByteReadPacket? = metadata