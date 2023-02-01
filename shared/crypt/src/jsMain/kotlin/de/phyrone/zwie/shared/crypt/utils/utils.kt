package de.phyrone.zwie.shared.crypt.utils

import global.CryptoJS.enc.Hex
import global.CryptoJS.lib.WordArray
import org.khronos.webgl.Int8Array
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.get

fun ByteArray.toWordArray(): WordArray = WordArray.create(this.unsafeCast<Array<Number>>())

fun WordArray.toByteArray() = ByteArray(sigBytes.toInt()) { pos ->
    (words[pos.ushr(2)].toInt().shr(24 - (pos % 4) * 8) and 0xFF).toByte()
}

fun ByteArray.toHex(): String = Hex.stringify(this.toWordArray())

fun ByteArray.toUint8Array(): Uint8Array = Uint8Array(this.toTypedArray())

fun Uint8Array.toByteArray(): ByteArray = ByteArray(this.length) { this[it] }

fun Uint8Array.toInt8Array(): Int8Array = Int8Array(this.buffer)

fun Int8Array.toByteArray(): ByteArray = ByteArray(this.length) { this[it] }

fun Int8Array.toUint8Array(): Uint8Array = Uint8Array(this.buffer)

fun ByteArray.toInt8Array(): Int8Array = Int8Array(this.toTypedArray())