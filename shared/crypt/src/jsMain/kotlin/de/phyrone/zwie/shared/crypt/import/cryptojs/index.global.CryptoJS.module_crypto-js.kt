@file:JsModule("crypto-js")
@file:JsNonModule
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
package global.CryptoJS

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
import HasherHelper
import HmacHasherHelper
import WordArray
import KDFOption
import CipherHelper
import global.CryptoJS.algo.`T$0`

external var MD5: HasherHelper

external var HmacMD5: HmacHasherHelper

external var SHA1: HasherHelper

external var HmacSHA1: HmacHasherHelper

external var SHA256: HasherHelper

external var HmacSHA256: HmacHasherHelper

external var SHA224: HasherHelper

external var HmacSHA224: HmacHasherHelper

external var SHA512: HasherHelper

external var HmacSHA512: HmacHasherHelper

external var SHA384: HasherHelper

external var HmacSHA384: HmacHasherHelper

external var SHA3: HasherHelper

external var HmacSHA3: HmacHasherHelper

external var RIPEMD160: HasherHelper

external var HmacRIPEMD160: HmacHasherHelper

external fun PBKDF2(password: WordArray, salt: WordArray, cfg: KDFOption = definedExternally): WordArray

external fun PBKDF2(password: WordArray, salt: WordArray): WordArray

external fun PBKDF2(password: WordArray, salt: String, cfg: KDFOption = definedExternally): WordArray

external fun PBKDF2(password: WordArray, salt: String): WordArray

external fun PBKDF2(password: String, salt: WordArray, cfg: KDFOption = definedExternally): WordArray

external fun PBKDF2(password: String, salt: WordArray): WordArray

external fun PBKDF2(password: String, salt: String, cfg: KDFOption = definedExternally): WordArray

external fun PBKDF2(password: String, salt: String): WordArray

external var AES: CipherHelper

external var DES: CipherHelper

external var TripleDES: CipherHelper

external var RC4: CipherHelper

external var RC4Drop: CipherHelper

external var Rabbit: CipherHelper

external var RabbitLegacy: CipherHelper

external fun EvpKDF(password: WordArray, salt: WordArray, cfg: `T$0` = definedExternally): WordArray

external fun EvpKDF(password: WordArray, salt: WordArray): WordArray

external fun EvpKDF(password: WordArray, salt: String, cfg: `T$0` = definedExternally): WordArray

external fun EvpKDF(password: WordArray, salt: String): WordArray

external fun EvpKDF(password: String, salt: WordArray, cfg: `T$0` = definedExternally): WordArray

external fun EvpKDF(password: String, salt: WordArray): WordArray

external fun EvpKDF(password: String, salt: String, cfg: `T$0` = definedExternally): WordArray

external fun EvpKDF(password: String, salt: String): WordArray