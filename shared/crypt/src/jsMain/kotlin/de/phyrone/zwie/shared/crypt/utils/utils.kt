package de.phyrone.zwie.shared.crypt.utils

import global.CryptoJS.enc.Hex
import global.CryptoJS.lib.WordArray
import org.khronos.webgl.Uint8Array

fun ByteArray.toWordArray(): WordArray = WordArray.create(this.unsafeCast<Array<Number>>())

fun WordArray.toByteArray() = ByteArray(sigBytes.toInt()) { pos ->
    (words[pos.ushr(2)].toInt().shr(24 - (pos % 4) * 8) and 0xFF).toByte()
}