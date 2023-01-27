@file:JsQualifier("global.CryptoJS.lib")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package global.CryptoJS.lib

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
import Encoder
import HasherStatic
import HasherHelper
import HmacHasherHelper
import CipherHelper
import CipherStatic
import Mode
import Padding
import Format
import CipherOption

external object Base {
    fun extend(overrides: Any?): Any
    fun create(vararg args: Any): Any
    fun mixIn(properties: Any?): Any
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface WordArray {
    var words: Array<Number>
    var sigBytes: Number
    fun toString(encoder: Encoder = definedExternally): String
    fun concat(wordArray: WordArray): WordArray /* this */
    fun clamp()
    fun clone(): WordArray

    companion object {
        fun create(words: Array<Number> = definedExternally, sigBytes: Number = definedExternally): WordArray
        fun random(nBytes: Number): WordArray
    }
}

external var BufferedBlockAlgorithm: Any

external object Hasher {
    fun _createHelper(hasher: HasherStatic): HasherHelper
    fun _createHmacHelper(hasher: HasherStatic): HmacHasherHelper
}

external object Cipher {
    fun _createHelper(cipher: Cipher): CipherHelper
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface CipherParams {
    var ciphertext: WordArray
    var key: WordArray
    var iv: WordArray
    var salt: WordArray
    var algorithm: CipherStatic
    var mode: Mode
    var padding: Padding
    var blockSize: Number
    var formatter: Format
    fun toString(formatter: Format = definedExternally): String

    companion object {
        fun create(cipherParams: CipherParamsPartial): CipherParams
    }
}

external interface CipherParamsPartial {
    var ciphertext: WordArray?
        get() = definedExternally
        set(value) = definedExternally
    var key: WordArray?
        get() = definedExternally
        set(value) = definedExternally
    var iv: WordArray?
        get() = definedExternally
        set(value) = definedExternally
    var salt: WordArray?
        get() = definedExternally
        set(value) = definedExternally
    var algorithm: CipherStatic?
        get() = definedExternally
        set(value) = definedExternally
    var mode: Mode?
        get() = definedExternally
        set(value) = definedExternally
    var padding: Padding?
        get() = definedExternally
        set(value) = definedExternally
    var blockSize: Number?
        get() = definedExternally
        set(value) = definedExternally
    var formatter: Format?
        get() = definedExternally
        set(value) = definedExternally
    var toString: ((formatter: Format) -> String)?
        get() = definedExternally
        set(value) = definedExternally
}

external interface StreamCipher {
    var blockSize: Number
}

external var BlockCipherMode: Any

external object SerializableCipher {
    fun encrypt(
        cipher: CipherStatic,
        message: Any /* WordArray | String */,
        key: WordArray,
        cfg: CipherOption = definedExternally
    ): CipherParams

    fun decrypt(
        cipher: CipherStatic,
        ciphertext: Any /* WordArray | String */,
        key: WordArray,
        cfg: CipherOption = definedExternally
    ): CipherParams

    fun _parse(ciphertext: Any /* CipherParams | String */, format: Format): CipherParams
}

external object PasswordBasedCipher {
    fun encrypt(
        cipher: CipherStatic,
        message: Any /* WordArray | String */,
        password: String,
        cfg: CipherOption = definedExternally
    ): CipherParams

    fun decrypt(
        cipher: CipherStatic,
        ciphertext: Any /* CipherParams | String */,
        password: String,
        cfg: CipherOption = definedExternally
    ): WordArray
}