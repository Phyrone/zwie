@file:JsQualifier("global.CryptoJS.x64")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
package global.CryptoJS.x64

import X64Word
import X64WordArray
import kotlin.js.*
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface Word {
    fun not(): X64Word
    fun and(word: X64Word): X64Word
    fun or(word: X64Word): X64Word
    fun xor(word: X64Word): X64Word
    fun shiftL(n: Number): X64Word
    fun shiftR(n: Number): X64Word
    fun rotL(n: Number): X64Word
    fun rotR(n: Number): X64Word
    fun add(word: X64Word): X64Word

    companion object {
        fun create(high: Number, low: Number): X64Word
    }
}

external object WordArray {
    fun create(words: Array<X64WordArray> = definedExternally, sigBytes: Number = definedExternally): X64WordArray
}