package de.phyrone.zwie.shared.crypt.import.common

import org.khronos.webgl.ArrayBuffer


@JsModule("secure-random")
@JsNonModule
external object SecureRandomJS {

    fun randomArray(size: Int): ByteArray
    @OptIn(ExperimentalUnsignedTypes::class)
    fun randomUint8Array(size: Int): UByteArray
    fun randomBuffer(size: Int) : ArrayBuffer


}