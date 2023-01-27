@file:JsQualifier("enums")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
package enums

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

external fun read(type: Any, e: armor): String /* "multipartSection" | "multipartLast" | "signed" | "message" | "publicKey" | "privateKey" */

external fun read(type: Any, e: compression): String /* "uncompressed" | "zip" | "zlib" | "bzip2" */

external fun read(type: Any, e: hash): String /* "md5" | "sha1" | "ripemd" | "sha256" | "sha384" | "sha512" | "sha224" */

external fun read(type: Any, e: packet): String /* "publicKeyEncryptedSessionKey" | "signature" | "symEncryptedSessionKey" | "onePassSignature" | "secretKey" | "publicKey" | "secretSubkey" | "compressed" | "symmetricallyEncrypted" | "marker" | "literal" | "trust" | "userID" | "publicSubkey" | "userAttribute" | "symEncryptedIntegrityProtected" | "modificationDetectionCode" | "AEADEncryptedDataPacket" */

external fun read(type: Any, e: publicKey): String /* "rsaEncryptSign" | "rsaEncrypt" | "rsaSign" | "elgamal" | "dsa" | "ecdh" | "ecdsa" | "eddsa" | "aedh" | "aedsa" */

external fun read(type: Any, e: symmetric): String /* "plaintext" | "idea" | "tripledes" | "cast5" | "blowfish" | "aes128" | "aes192" | "aes256" | "twofish" */

external fun read(type: Any, e: keyStatus): String /* "invalid" | "expired" | "revoked" | "valid" | "noSelfCert" */

external fun read(type: Any, e: keyFlags): String /* "certifyKeys" | "signData" | "encryptCommunication" | "encryptStorage" | "splitPrivateKey" | "authentication" | "sharedPrivateKey" */

external enum class armor {
    multipartSection /* = 0 */,
    multipartLast /* = 1 */,
    signed /* = 2 */,
    message /* = 3 */,
    publicKey /* = 4 */,
    privateKey /* = 5 */,
    signature /* = 6 */
}

external enum class reasonForRevocation {
    noReason /* = 0 */,
    keySuperseded /* = 1 */,
    keyCompromised /* = 2 */,
    keyRetired /* = 3 */,
    userIDInvalid /* = 32 */
}

external enum class compression {
    uncompressed /* = 0 */,
    zip /* = 1 */,
    zlib /* = 2 */,
    bzip2 /* = 3 */
}

external enum class hash {
    md5 /* = 1 */,
    sha1 /* = 2 */,
    ripemd /* = 3 */,
    sha256 /* = 8 */,
    sha384 /* = 9 */,
    sha512 /* = 10 */,
    sha224 /* = 11 */
}

external enum class packet {
    publicKeyEncryptedSessionKey /* = 1 */,
    signature /* = 2 */,
    symEncryptedSessionKey /* = 3 */,
    onePassSignature /* = 4 */,
    secretKey /* = 5 */,
    publicKey /* = 6 */,
    secretSubkey /* = 7 */,
    compressedData /* = 8 */,
    symmetricallyEncryptedData /* = 9 */,
    marker /* = 10 */,
    literalData /* = 11 */,
    trust /* = 12 */,
    userID /* = 13 */,
    publicSubkey /* = 14 */,
    userAttribute /* = 17 */,
    symEncryptedIntegrityProtectedData /* = 18 */,
    modificationDetectionCode /* = 19 */,
    aeadEncryptedData /* = 20 */
}

external enum class publicKey {
    rsaEncryptSign /* = 1 */,
    rsaEncrypt /* = 2 */,
    rsaSign /* = 3 */,
    elgamal /* = 16 */,
    dsa /* = 17 */,
    ecdh /* = 18 */,
    ecdsa /* = 19 */,
    eddsa /* = 22 */,
    aedh /* = 23 */,
    aedsa /* = 24 */
}

external enum class curve {
    p256 /* = 'p256' */,
    p384 /* = 'p384' */,
    p521 /* = 'p521' */,
    ed25519 /* = 'ed25519' */,
    curve25519 /* = 'curve25519' */,
    secp256k1 /* = 'secp256k1' */,
    brainpoolP256r1 /* = 'brainpoolP256r1' */,
    brainpoolP384r1 /* = 'brainpoolP384r1' */,
    brainpoolP512r1 /* = 'brainpoolP512r1' */
}

external enum class symmetric {
    plaintext /* = 0 */,
    idea /* = 1 */,
    tripledes /* = 2 */,
    cast5 /* = 3 */,
    blowfish /* = 4 */,
    aes128 /* = 7 */,
    aes192 /* = 8 */,
    aes256 /* = 9 */,
    twofish /* = 10 */
}

external enum class keyStatus {
    invalid /* = 0 */,
    expired /* = 1 */,
    revoked /* = 2 */,
    valid /* = 3 */,
    noSelfCert /* = 4 */
}

external enum class keyFlags {
    certifyKeys /* = 1 */,
    signData /* = 2 */,
    encryptCommunication /* = 4 */,
    encryptStorage /* = 8 */,
    splitPrivateKey /* = 16 */,
    authentication /* = 32 */,
    sharedPrivateKey /* = 128 */
}

external enum class signature {
    binary /* = 0 */,
    text /* = 1 */,
    standalone /* = 2 */,
    certGeneric /* = 16 */,
    certPersona /* = 17 */,
    certCasual /* = 18 */,
    certPositive /* = 19 */,
    certRevocation /* = 48 */,
    subkeyBinding /* = 24 */,
    keyBinding /* = 25 */,
    key /* = 31 */,
    keyRevocation /* = 32 */,
    subkeyRevocation /* = 40 */,
    timestamp /* = 64 */,
    thirdParty /* = 80 */
}

external enum class aead {
    eax /* = 1 */,
    ocb /* = 2 */,
    experimentalGCM /* = 100 */
}

external enum class literal {
    binary /* = 98 */,
    text /* = 116 */,
    utf8 /* = 117 */,
    mime /* = 109 */
}