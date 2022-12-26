package de.phyrone.zwie.shared.common

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import org.w3c.files.Blob

fun ByteArray.toBase64(): String = js("btoa(String.fromCharCode.apply(null, this))").unsafeCast<String>()

fun String.fromBase64(): ByteArray = js("new Uint8Array(atob(this).split('').map(function(c) { return c.charCodeAt(0); }))").unsafeCast<ByteArray>()

fun ByteArray.toHex(): String = joinToString("") { it.toInt().toString(16).padStart(2, '0') }

fun String.fromHex(): ByteArray = chunked(2).map { it.toInt(16).toByte() }.toByteArray()

fun ByteArray.toArrayBuffer(): ArrayBuffer = this.unsafeCast<Uint8Array>().buffer
fun Any.toByteArray(): ByteArray = js("new Uint8Array(this)") as ByteArray

fun ByteArray.toBlob(): Blob = js("new Blob([this])") as Blob

fun Blob.toByteArray(): ByteArray = js("new Uint8Array(this)") as ByteArray