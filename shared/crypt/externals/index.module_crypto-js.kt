@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")

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
import global.CryptoJS.x64.Word

typealias WordArray = CryptoJS.lib.WordArray

typealias CipherParams = CryptoJS.lib.CipherParams

typealias X64Word = Word

external interface Encoder {
    fun stringify(wordArray: WordArray): String
    fun parse(str: String): WordArray
}

external interface BufferedBlockAlgorithm {
    var _minBufferSize: Number
    fun reset()
    fun _append(data: WordArray)
    fun _append(data: String)
    fun _process(doFlush: Boolean = definedExternally): WordArray
    fun clone(): BufferedBlockAlgorithm
}

external interface Hasher {
    var blockSize: Number
    fun reset()
    fun update(messageUpdate: WordArray): Hasher /* this */
    fun update(messageUpdate: String): Hasher /* this */
    fun finalize(messageUpdate: WordArray = definedExternally): WordArray
    fun finalize(): WordArray
    fun finalize(messageUpdate: String = definedExternally): WordArray
}

external interface HasherStatic {
    fun create(cfg: Any? = definedExternally): Hasher
}

external interface HasherHelper {
    @nativeInvoke
    operator fun invoke(message: WordArray, cfg: Any? = definedExternally): WordArray
    @nativeInvoke
    operator fun invoke(message: WordArray): WordArray
    @nativeInvoke
    operator fun invoke(message: String, cfg: Any? = definedExternally): WordArray
    @nativeInvoke
    operator fun invoke(message: String): WordArray
}

external interface HmacHasherHelper {
    @nativeInvoke
    operator fun invoke(message: WordArray, key: WordArray): WordArray
    @nativeInvoke
    operator fun invoke(message: WordArray, key: String): WordArray
    @nativeInvoke
    operator fun invoke(message: String, key: WordArray): WordArray
    @nativeInvoke
    operator fun invoke(message: String, key: String): WordArray
}

external interface Cipher {
    var keySize: Number
    var ivSize: Number
    var _ENC_XFORM_MODE: Number
    var _DEV_XFORM_MODE: Number
    fun reset()
    fun process(dataUpdate: WordArray): WordArray
    fun process(dataUpdate: String): WordArray
    fun finalize(dataUpdate: WordArray = definedExternally): WordArray
    fun finalize(): WordArray
    fun finalize(dataUpdate: String = definedExternally): WordArray
}

external interface CipherStatic {
    fun createEncryptor(key: WordArray, cfg: CipherOption = definedExternally): Cipher
    fun createDecryptor(key: WordArray, cfg: CipherOption = definedExternally): Cipher
    fun create(xformMode: Number, key: WordArray, cfg: CipherOption = definedExternally): Cipher
}

external interface CipherHelper {
    fun encrypt(message: WordArray, key: WordArray, cfg: CipherOption = definedExternally): CipherParams
    fun encrypt(message: WordArray, key: WordArray): CipherParams
    fun encrypt(message: WordArray, key: String, cfg: CipherOption = definedExternally): CipherParams
    fun encrypt(message: WordArray, key: String): CipherParams
    fun encrypt(message: String, key: WordArray, cfg: CipherOption = definedExternally): CipherParams
    fun encrypt(message: String, key: WordArray): CipherParams
    fun encrypt(message: String, key: String, cfg: CipherOption = definedExternally): CipherParams
    fun encrypt(message: String, key: String): CipherParams
    fun decrypt(ciphertext: CipherParams, key: WordArray, cfg: CipherOption = definedExternally): WordArray
    fun decrypt(ciphertext: CipherParams, key: WordArray): WordArray
    fun decrypt(ciphertext: CipherParams, key: String, cfg: CipherOption = definedExternally): WordArray
    fun decrypt(ciphertext: CipherParams, key: String): WordArray
    fun decrypt(ciphertext: String, key: WordArray, cfg: CipherOption = definedExternally): WordArray
    fun decrypt(ciphertext: String, key: WordArray): WordArray
    fun decrypt(ciphertext: String, key: String, cfg: CipherOption = definedExternally): WordArray
    fun decrypt(ciphertext: String, key: String): WordArray
}

external interface CipherOption {
    var iv: WordArray?
        get() = definedExternally
        set(value) = definedExternally
    var format: Format?
        get() = definedExternally
        set(value) = definedExternally
    @nativeGetter
    operator fun get(key: String): Any?
    @nativeSetter
    operator fun set(key: String, value: Any)
}

external interface Mode {
    fun processBlock(words: Array<Number>, offset: Number)
}

external interface ModeStatic {
    fun create(cipher: Cipher, iv: Array<Number>): Mode
}

external interface BlockCipherMode {
    fun createEncryptor(cipher: Cipher): Mode
    var Encryptor: ModeStatic
    var Decryptor: ModeStatic
    fun createEncryptor(cipher: Cipher, iv: Array<Number>): Mode
    fun createDecryptor(cipher: Cipher, iv: Array<Number>): Mode
}

external interface Padding {
    fun pad(data: WordArray, blockSize: Number)
    fun unpad(data: WordArray)
}

external interface BlockCipher {
    var blockSize: Number
}

external interface BlockCipherOption {
    var mode: Mode
    var padding: Padding
}

external interface Format {
    fun stringify(cipherParams: CipherParams): String
    fun parse(str: String): CipherParams
}

external interface X64WordArray {
    var words: Array<Number>
    var sigBytes: Number
    fun toX32(): WordArray
    fun clone(): X64WordArray
}

external interface Base {
    fun clone(): Base /* this */
}

external interface KDFOption {
    var keySize: Number?
        get() = definedExternally
        set(value) = definedExternally
    var hasher: HasherStatic?
        get() = definedExternally
        set(value) = definedExternally
    var iterations: Number?
        get() = definedExternally
        set(value) = definedExternally
}