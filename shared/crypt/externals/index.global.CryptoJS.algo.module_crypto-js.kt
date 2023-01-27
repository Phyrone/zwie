@file:JsQualifier("global.CryptoJS.algo")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
package global.CryptoJS.algo

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
import HasherStatic
import WordArray
import KDFOption
import CipherStatic

external var MD5: HasherStatic

external var SHA1: HasherStatic

external var SHA256: HasherStatic

external var SHA224: HasherStatic

external var SHA512: HasherStatic

external var SHA384: HasherStatic

external var SHA3: HasherStatic

external var RIPEMD160: HasherStatic

external open class HMAC {
    open fun reset()
    open fun update(messageUpdate: WordArray): HMAC /* this */
    open fun update(messageUpdate: String): HMAC /* this */
    open fun finalize(messageUpdate: WordArray = definedExternally): WordArray
    open fun finalize(): WordArray
    open fun finalize(messageUpdate: String = definedExternally): WordArray

    companion object {
        fun create(hasher: HasherStatic, key: WordArray): HMAC
        fun create(hasher: HasherStatic, key: String): HMAC
    }
}

external open class PBKDF2 {
    open fun compute(password: WordArray, salt: WordArray): WordArray
    open fun compute(password: String, salt: WordArray): WordArray

    companion object {
        fun create(cfg: KDFOption = definedExternally): PBKDF2
    }
}

external interface `T$0` {
    var keySize: Number
    var hasher: HasherStatic?
        get() = definedExternally
        set(value) = definedExternally
    var iterations: Number
}

external open class EvpKDF {
    open fun compute(password: WordArray, salt: WordArray): WordArray
    open fun compute(password: String, salt: WordArray): WordArray

    companion object {
        fun create(cfg: `T$0` = definedExternally): EvpKDF
    }
}

external var AES: CipherStatic

external var DES: CipherStatic

external var TripleDES: CipherStatic

external var RC4: CipherStatic

external var RC4Drop: CipherStatic

external var Rabbit: CipherStatic

external var RabbitLegacy: CipherStatic