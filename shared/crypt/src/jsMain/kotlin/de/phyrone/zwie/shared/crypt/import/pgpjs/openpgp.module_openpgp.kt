@file:JsModule("openpgp")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package global.GPGJS
import de.phyrone.zwie.shared.crypt.import.common.Partial
import de.phyrone.zwie.shared.crypt.import.pgpjs.AnyKeyPacket
import de.phyrone.zwie.shared.crypt.import.pgpjs.PacketList
import global.GPGJS.enums.*
import org.khronos.webgl.Uint8Array
import kotlin.js.Date
import kotlin.js.Promise

external interface `T$1` {
    var armoredKey: String
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun readKey(options: `T$1`): Promise<Key>

external interface `T$2` {
    var binaryKey: Uint8Array
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun readKey(options: `T$2`): Promise<Key>

external interface `T$3` {
    var armoredKeys: String
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun readKeys(options: `T$3`): Promise<Array<Key>>

external interface `T$4` {
    var binaryKeys: Uint8Array
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun readKeys(options: `T$4`): Promise<Array<Key>>

external fun readPrivateKey(options: `T$1`): Promise<PrivateKey>

external fun readPrivateKey(options: `T$2`): Promise<PrivateKey>

external fun readPrivateKeys(options: `T$3`): Promise<Array<PrivateKey>>

external fun readPrivateKeys(options: `T$4`): Promise<Array<PrivateKey>>

external fun generateKey(options: GenerateKeyOptions /* GenerateKeyOptions & `T$5` | GenerateKeyOptions & `T$7` | GenerateKeyOptions & `T$8` */): dynamic /* Promise */

external interface `T$9` {
    var privateKey: PrivateKey
    var passphrase: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun decryptKey(options: `T$9`): Promise<PrivateKey>

external fun encryptKey(options: `T$9`): Promise<PrivateKey>

external interface `T$10` {
    var privateKey: PrivateKey
    var userIDs: dynamic /* UserID? | Array<UserID>? */
        get() = definedExternally
        set(value) = definedExternally
    var passphrase: String?
        get() = definedExternally
        set(value) = definedExternally
    var keyExpirationTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String? /* "armored" */
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun reformatKey(options: `T$10`): Promise<SerializedKeyPair<String> /* SerializedKeyPair<String> & `T$6` */>

external interface `T$11` {
    var privateKey: PrivateKey
    var userIDs: dynamic /* UserID? | Array<UserID>? */
        get() = definedExternally
        set(value) = definedExternally
    var passphrase: String?
        get() = definedExternally
        set(value) = definedExternally
    var keyExpirationTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String /* "binary" */
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun reformatKey(options: `T$11`): Promise<SerializedKeyPair<Uint8Array> /* SerializedKeyPair<Uint8Array> & `T$6` */>

external interface `T$12` {
    var privateKey: PrivateKey
    var userIDs: dynamic /* UserID? | Array<UserID>? */
        get() = definedExternally
        set(value) = definedExternally
    var passphrase: String?
        get() = definedExternally
        set(value) = definedExternally
    var keyExpirationTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String /* "object" */
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun reformatKey(options: `T$12`): Promise<KeyPair /* KeyPair & `T$6` */>

external interface `T$13` {
    var key: PrivateKey
    var reasonForRevocation: ReasonForRevocation?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String? /* "armored" */
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun revokeKey(options: `T$13`): Promise<SerializedKeyPair<String>>

external interface `T$14` {
    var key: PrivateKey
    var reasonForRevocation: ReasonForRevocation?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String /* "binary" */
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun revokeKey(options: `T$14`): Promise<SerializedKeyPair<Uint8Array>>

external interface `T$15` {
    var key: PrivateKey
    var reasonForRevocation: ReasonForRevocation?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String /* "object" */
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun revokeKey(options: `T$15`): Promise<KeyPair>

external interface `T$16` {
    var key: PrivateKey
    var revocationCertificate: String
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String? /* "armored" */
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun revokeKey(options: `T$16`): Promise<SerializedKeyPair<String>>

external interface `T$17` {
    var key: PrivateKey
    var revocationCertificate: String
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String /* "binary" */
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun revokeKey(options: `T$17`): Promise<SerializedKeyPair<Uint8Array>>

external interface `T$18` {
    var key: PrivateKey
    var revocationCertificate: String
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String /* "object" */
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun revokeKey(options: `T$18`): Promise<KeyPair>

external interface `T$19` {
    var key: PublicKey
    var revocationCertificate: String
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String? /* "armored" */
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$20` {
    var publicKey: String
    var privateKey: Any?
}

external fun revokeKey(options: `T$19`): Promise<`T$20`>

external interface `T$21` {
    var key: PublicKey
    var revocationCertificate: String
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String /* "binary" */
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$22` {
    var publicKey: Uint8Array
    var privateKey: Any?
}

external fun revokeKey(options: `T$21`): Promise<`T$22`>

external interface `T$23` {
    var key: PublicKey
    var revocationCertificate: String
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String /* "object" */
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$24` {
    var publicKey: PublicKey
    var privateKey: Any?
}

external fun revokeKey(options: `T$23`): Promise<`T$24`>

external interface `T$25` {
    var keyID: KeyID
    var valid: Boolean?
}

external interface `T$26` {
    var userID: String
    var keyID: KeyID
    var valid: Boolean?
}

external open class Key {
    open var keyPacket: dynamic /* PublicKeyPacket | SecretKeyPacket */
    open var subkeys: Array<Subkey>
    open var users: Array<User>
    open var revocationSignatures: Array<SignaturePacket>
    open fun write(): Uint8Array
    open fun armor(config: Config = definedExternally): String
    open fun getExpirationTime(
        userID: UserID = definedExternally,
        config: Config = definedExternally
    ): Promise<dynamic /* Date? | Any? */>

    open fun getKeyIDs(): Array<KeyID>
    open fun getPrimaryUser(
        date: Date = definedExternally,
        userID: UserID = definedExternally,
        config: Config = definedExternally
    ): Promise<PrimaryUser>

    open fun getUserIDs(): Array<String>
    open fun isPrivate(): Boolean
    open fun toPublic(): PublicKey
    open fun update(
        sourceKey: PrivateKey,
        date: Date = definedExternally,
        config: Config = definedExternally
    ): Promise<PrivateKey>

    open fun update(sourceKey: PrivateKey): Promise<PrivateKey>
    open fun update(sourceKey: PrivateKey, date: Date = definedExternally): Promise<PrivateKey>
    open fun update(
        sourceKey: PublicKey,
        date: Date = definedExternally,
        config: Config = definedExternally
    ): Promise<PublicKey>

    open fun update(sourceKey: PublicKey): Promise<PublicKey>
    open fun update(sourceKey: PublicKey, date: Date = definedExternally): Promise<PublicKey>
    open fun signPrimaryUser(
        privateKeys: Array<PrivateKey>,
        date: Date = definedExternally,
        userID: UserID = definedExternally,
        config: Config = definedExternally
    ): Promise<Key /* this */>

    open fun signAllUsers(
        privateKeys: Array<PrivateKey>,
        date: Date = definedExternally,
        config: Config = definedExternally
    ): Promise<Key /* this */>

    open fun verifyPrimaryKey(
        date: Date = definedExternally,
        userID: UserID = definedExternally,
        config: Config = definedExternally
    ): Promise<Unit>

    open fun verifyPrimaryUser(
        publicKeys: Array<PublicKey>,
        date: Date = definedExternally,
        userIDs: UserID = definedExternally,
        config: Config = definedExternally
    ): Promise<Array<`T$25`>>

    open fun verifyAllUsers(
        publicKeys: Array<PublicKey>,
        date: Date = definedExternally,
        config: Config = definedExternally
    ): Promise<Array<`T$26`>>

    open fun isRevoked(
        signature: SignaturePacket = definedExternally,
        key: AnyKeyPacket = definedExternally,
        date: Date = definedExternally,
        config: Config = definedExternally
    ): Promise<Boolean>

    open fun getRevocationCertificate(
        date: Date = definedExternally,
        config: Config = definedExternally
    ): Promise<dynamic /* String? | WebStream<String>? | NodeStream<String>? */>

    open fun getEncryptionKey(
        keyID: KeyID = definedExternally,
        date: Date? = definedExternally,
        userID: UserID = definedExternally,
        config: Config = definedExternally
    ): Promise<dynamic /* this | Subkey */>

    open fun getSigningKey(
        keyID: KeyID = definedExternally,
        date: Date? = definedExternally,
        userID: UserID = definedExternally,
        config: Config = definedExternally
    ): Promise<dynamic /* this | Subkey */>

    open fun getKeys(keyID: KeyID = definedExternally): Array<dynamic /* this | Subkey */>
    open fun getSubkeys(keyID: KeyID = definedExternally): Array<Subkey>
    open fun getFingerprint(): String
    open fun getCreationTime(): Date
    open fun getAlgorithmInfo(): AlgorithmInfo
    open fun getKeyID(): KeyID
    open fun toPacketList(): PacketList<dynamic /* PublicKeyPacket | PublicSubkeyPacket | SecretKeyPacket | SecretSubkeyPacket | UserIDPacket | UserAttributePacket | SignaturePacket */>
}

external open class PublicKey(packetlist: PacketList<Any /* BasePacket | UnparseablePacket */>) : Key

external open class PrivateKey(packetlist: PacketList<Any /* BasePacket | UnparseablePacket */>) : PublicKey {
    open fun revoke(
        reason: ReasonForRevocation = definedExternally,
        date: Date = definedExternally,
        config: Config = definedExternally
    ): Promise<PrivateKey>

    open fun isDecrypted(): Boolean
    open fun addSubkey(options: SubkeyOptions): Promise<PrivateKey>
    open fun getDecryptionKeys(
        keyID: KeyID = definedExternally,
        date: Date? = definedExternally,
        userID: UserID = definedExternally,
        config: Config = definedExternally
    ): Promise<dynamic /* PrivateKey | Subkey */>

    override fun update(sourceKey: PublicKey, date: Date, config: Config): Promise<PrivateKey>
}

external open class Subkey {
    constructor(subkeyPacket: SecretSubkeyPacket, mainKey: PublicKey)
    constructor(subkeyPacket: PublicSubkeyPacket, mainKey: PublicKey)

    open var keyPacket: dynamic /* SecretSubkeyPacket | PublicSubkeyPacket */
    open var mainKey: PublicKey
    open var bindingSignatures: Array<SignaturePacket>
    open var revocationSignatures: Array<SignaturePacket>
    open fun verify(date: Date = definedExternally, config: Config = definedExternally): Promise<SignaturePacket>
    open fun isDecrypted(): Boolean
    open fun getFingerprint(): String
    open fun getCreationTime(): Date
    open fun getAlgorithmInfo(): AlgorithmInfo
    open fun getKeyID(): KeyID
}

external interface User {
    var userID: UserIDPacket?
    var userAttribute: UserAttributePacket?
    var selfCertifications: Array<SignaturePacket>
    var otherCertifications: Array<SignaturePacket>
    var revocationSignatures: Array<SignaturePacket>
}

external interface PrimaryUser {
    var index: Number
    var user: User
}

external interface AlgorithmInfo {
    var algorithm: String /* "rsaEncryptSign" | "rsaEncrypt" | "rsaSign" | "elgamal" | "dsa" | "ecdh" | "ecdsa" | "eddsa" | "aedh" | "aedsa" */
    var bits: Number?
        get() = definedExternally
        set(value) = definedExternally
    var curve: String? /* "ed25519" | "curve25519" | "p256" | "p384" | "p521" | "secp256k1" | "brainpoolP256r1" | "brainpoolP384r1" | "brainpoolP512r1" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$27` {
    var armoredSignature: String
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun readSignature(options: `T$27`): Promise<Signature>

external interface `T$28` {
    var binarySignature: Uint8Array
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun readSignature(options: `T$28`): Promise<Signature>

external open class Signature(packetlist: PacketList<SignaturePacket>) {
    open var packets: PacketList<SignaturePacket>
    open fun write(): dynamic /* Uint8Array | WebStream<Uint8Array> | NodeStream<Uint8Array> */
    open fun armor(config: Config = definedExternally): String
    open fun getSigningKeyIDs(): Array<KeyID>
}

external interface VerificationResult {
    var keyID: KeyID
    var verified: Promise<Boolean>
    var signature: Promise<Signature>
}

external interface `T$29` {
    var cleartextMessage: String
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun readCleartextMessage(options: `T$29`): Promise<CleartextMessage>

external interface `T$30` {
    var text: String
}

external fun createCleartextMessage(options: `T$30`): Promise<CleartextMessage>

external open class CleartextMessage {
    open fun armor(config: Config = definedExternally): String
    open fun getSigningKeyIDs(): Array<KeyID>
    open fun getText(): String
    open fun sign(
        privateKeys: Array<PrivateKey>,
        signature: Signature = definedExternally,
        signingKeyIDs: Array<KeyID> = definedExternally,
        date: Date = definedExternally,
        userIDs: Array<UserID> = definedExternally,
        config: Config = definedExternally
    )

    open fun verify(
        keys: Array<PublicKey>,
        date: Date = definedExternally,
        config: Config = definedExternally
    ): Promise<Array<VerificationResult>>
}

external interface `T$31` {
    var encryptionKeys: dynamic /* PublicKey | Array<PublicKey> */
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var encryptionUserIDs: dynamic /* UserID? | Array<UserID>? */
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun generateSessionKey(options: `T$31`): Promise<SessionKey>

external fun encryptSessionKey(options: EncryptSessionKeyOptions /* EncryptSessionKeyOptions & `T$5` | EncryptSessionKeyOptions & `T$7` | EncryptSessionKeyOptions & `T$8` */): dynamic /* Promise */

external interface `T$32`<T> {
    var message: Message<T>
    var decryptionKeys: dynamic /* PrivateKey? | Array<PrivateKey>? */
        get() = definedExternally
        set(value) = definedExternally
    var passwords: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun <T> decryptSessionKeys(options: `T$32`<T>): Promise<Array<SessionKey>>

external interface `T$33`<T> {
    var armoredMessage: T
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun <T> readMessage(options: `T$33`<T>): Promise<Message<T>>

external interface `T$34`<T> {
    var binaryMessage: T
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external fun <T> readMessage(options: `T$34`<T>): Promise<Message<T>>

external interface `T$35`<T> {
    var text: T
    var filename: String?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String? /* "utf8" | "binary" | "text" | "mime" */
        get() = definedExternally
        set(value) = definedExternally
}

external fun <T> createMessage(options: `T$35`<T>): Promise<Message<T>>

external interface `T$36`<T> {
    var binary: T
    var filename: String?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var format: String? /* "utf8" | "binary" | "text" | "mime" */
        get() = definedExternally
        set(value) = definedExternally
}

external fun <T> createMessage(options: `T$36`<T>): Promise<Message<T>>

external fun <T> encrypt(options: EncryptOptions /* EncryptOptions & `T$37`<T> | EncryptOptions & `T$38`<T> | EncryptOptions & `T$39`<T> */): dynamic /* Promise | Promise */

external fun <T> sign(options: SignOptions /* SignOptions & `T$37`<T> | SignOptions & `T$38`<T> | SignOptions & `T$39`<T> */): dynamic /* Promise | Promise */

external fun sign(options: SignOptions /* SignOptions & `T$40` | SignOptions & `T$41` */): dynamic /* Promise | Promise */

external fun <T> decrypt(options: DecryptOptions /* DecryptOptions & `T$38`<T> | DecryptOptions & `T$43`<T> */): Promise<DecryptMessageResult /* DecryptMessageResult & `T$42` */>

external fun <T> verify(options: VerifyOptions /* VerifyOptions & `T$38`<T> | VerifyOptions & `T$43`<T> */): Promise<VerifyMessageResult /* VerifyMessageResult & `T$42` */>

external open class Message<T>(packetlist: PacketList<Any /* BasePacket | UnparseablePacket */>) {
    open var packets: PacketList<dynamic /* BasePacket | UnparseablePacket */>
    open fun write(): dynamic /* Uint8Array | WebStream<Uint8Array> | NodeStream<Uint8Array> */
    open fun armor(config: Config = definedExternally): String
    open fun decrypt(
        decryptionKeys: Array<PrivateKey> = definedExternally,
        passwords: Array<String> = definedExternally,
        sessionKeys: Array<SessionKey> = definedExternally,
        date: Date = definedExternally,
        config: Config = definedExternally
    ): Promise<Message<dynamic /* Uint8Array | String | WebStream<dynamic /* Uint8Array | String */> | NodeStream<dynamic /* Uint8Array | String */> */>>

    open fun encrypt(
        encryptionKeys: Array<PublicKey> = definedExternally,
        passwords: Array<String> = definedExternally,
        sessionKeys: Array<SessionKey> = definedExternally,
        wildcard: Boolean = definedExternally,
        encryptionKeyIDs: Array<KeyID> = definedExternally,
        date: Date = definedExternally,
        userIDs: Array<UserID> = definedExternally,
        config: Config = definedExternally
    ): Promise<Message<dynamic /* Uint8Array | String | WebStream<dynamic /* Uint8Array | String */> | NodeStream<dynamic /* Uint8Array | String */> */>>

    open fun getEncryptionKeyIDs(): Array<KeyID>
    open fun getLiteralData(): Any?
    open fun getSigningKeyIDs(): Array<KeyID>
    open fun getText(): Any?
    open fun getFilename(): String?
    open fun sign(
        signingKeys: Array<PrivateKey>,
        signature: Signature = definedExternally,
        signingKeyIDs: Array<KeyID> = definedExternally,
        date: Date = definedExternally,
        userIDs: Array<UserID> = definedExternally,
        config: Config = definedExternally
    ): Promise<Message<T>>

    open fun unwrapCompressed(): Message<T>
    open fun verify(
        verificationKeys: Array<PublicKey>,
        date: Date = definedExternally,
        config: Config = definedExternally
    ): Promise<Array<VerificationResult>>

    open fun appendSignature(detachedSignature: String, config: Config = definedExternally): Promise<Unit>
    open fun appendSignature(detachedSignature: String): Promise<Unit>
    open fun appendSignature(detachedSignature: Uint8Array, config: Config = definedExternally): Promise<Unit>
    open fun appendSignature(detachedSignature: Uint8Array): Promise<Unit>
}

external interface Config {
    var preferredHashAlgorithm: hash
    var preferredSymmetricAlgorithm: symmetric
    var preferredCompressionAlgorithm: compression
    var showVersion: Boolean
    var showComment: Boolean
    var deflateLevel: Number
    var aeadProtect: Boolean
    var allowUnauthenticatedMessages: Boolean
    var allowUnauthenticatedStream: Boolean
    var checksumRequired: Boolean
    var minRSABits: Number
    var passwordCollisionCheck: Boolean
    var revocationsExpire: Boolean
    var ignoreUnsupportedPackets: Boolean
    var ignoreMalformedPackets: Boolean
    var versionString: String
    var commentString: String
    var allowInsecureDecryptionWithSigningKeys: Boolean
    var allowInsecureVerificationWithReformattedKeys: Boolean
    var constantTimePKCS1Decryption: Boolean
    var constantTimePKCS1DecryptionSupportedSymmetricAlgorithms: Set<symmetric>
    var v5Keys: Boolean
    var preferredAEADAlgorithm: aead
    var aeadChunkSizeByte: Number
    var s2kIterationCountByte: Number
    var minBytesForWebCrypto: Number
    var maxUserIDLength: Number
    var knownNotations: Array<String>
    var useIndutnyElliptic: Boolean
    var rejectHashAlgorithms: Set<hash>
    var rejectMessageHashAlgorithms: Set<hash>
    var rejectPublicKeyAlgorithms: Set<publicKey>
    var rejectCurves: Set<curve>
}

external var config: Config

external interface PartialConfig : Partial<Config>

external open class BasePacket {
    open fun read(bytes: Uint8Array)
    open fun write(): Uint8Array

    companion object {
        var tag: packet
    }
}

external open class BasePublicKeyPacket : BasePacket {
    open var algorithm: publicKey
    open var created: Date
    open var version: Number
    open fun getAlgorithmInfo(): AlgorithmInfo
    open fun getFingerprint(): String
    open fun getFingerprintBytes(): Uint8Array?
    open fun hasSameFingerprintAs(other: BasePublicKeyPacket): Boolean
    open fun getCreationTime(): Date
    open fun getKeyID(): KeyID
    open fun isDecrypted(): Boolean
    open var publicParams: Any?
    open fun isSubkey(): Boolean
}

external open class PublicKeyPacket : BasePublicKeyPacket {
    override fun isSubkey(): Boolean

    companion object {
        var tag: Any
    }
}

external open class PublicSubkeyPacket : BasePublicKeyPacket {
    override fun isSubkey(): Boolean

    companion object {
        var tag: Any
    }
}

external open class BaseSecretKeyPacket : BasePublicKeyPacket {
    open var privateParams: Any?
    open fun encrypt(passphrase: String, config: Config = definedExternally): Promise<Unit>
    open fun decrypt(passphrase: String): Promise<Unit>
    open fun validate(): Promise<Unit>
    open fun isDummy(): Boolean
    open fun makeDummy(config: Config = definedExternally)
}

external open class SecretKeyPacket : BaseSecretKeyPacket {
    override fun isSubkey(): Boolean

    companion object {
        var tag: Any
    }
}

external open class SecretSubkeyPacket : BaseSecretKeyPacket {
    override fun isSubkey(): Boolean

    companion object {
        var tag: Any
    }
}

external open class CompressedDataPacket : BasePacket {
    open fun compress()
    open fun decompress(config: Config = definedExternally)

    companion object {
        var tag: Any
    }
}

external open class SymEncryptedIntegrityProtectedDataPacket : BasePacket {
    companion object {
        var tag: Any
    }
}

external open class AEADEncryptedDataPacket : BasePacket {
    open fun decrypt(sessionKeyAlgorithm: symmetric, sessionKey: Uint8Array, config: Config = definedExternally)
    open fun encrypt(sessionKeyAlgorithm: symmetric, sessionKey: Uint8Array, config: Config = definedExternally)
    open fun crypt(
        fn: Function<*>,
        sessionKey: Uint8Array,
        data: Uint8Array
    ): dynamic /* Uint8Array | WebStream<Uint8Array> | NodeStream<Uint8Array> */

    open fun crypt(
        fn: Function<*>,
        sessionKey: Uint8Array,
        data: LocalWebStream<Uint8Array>
    ): dynamic /* Uint8Array | WebStream<Uint8Array> | NodeStream<Uint8Array> */

    open fun crypt(
        fn: Function<*>,
        sessionKey: Uint8Array,
        data: LocalNodeStream<Uint8Array>
    ): dynamic /* Uint8Array | WebStream<Uint8Array> | NodeStream<Uint8Array> */

    companion object {
        var tag: Any
    }
}

external open class PublicKeyEncryptedSessionKeyPacket : BasePacket {
    open fun decrypt(keyPacket: SecretKeyPacket)
    open fun encrypt(keyPacket: PublicKeyPacket)

    companion object {
        var tag: Any
    }
}

external open class SymEncryptedSessionKey : BasePacket {
    open fun decrypt(passphrase: String): Promise<Unit>
    open fun encrypt(passphrase: String, config: Config = definedExternally): Promise<Unit>

    companion object {
        var tag: Any
    }
}

external open class LiteralDataPacket : BasePacket {
    open fun getText(clone: Boolean = definedExternally): dynamic /* String | WebStream<String> | NodeStream<String> */
    open fun getBytes(clone: Boolean = definedExternally): dynamic /* Uint8Array | WebStream<Uint8Array> | NodeStream<Uint8Array> */
    open fun setText(text: String, format: literal = definedExternally)
    open fun setText(text: String)
    open fun setText(text: LocalWebStream<String>, format: literal = definedExternally)
    open fun setText(text: LocalWebStream<String>)
    open fun setText(text: LocalNodeStream<String>, format: literal = definedExternally)
    open fun setText(text: LocalNodeStream<String>)
    open fun setBytes(bytes: Uint8Array, format: literal)
    open fun setBytes(bytes: LocalWebStream<Uint8Array>, format: literal)
    open fun setBytes(bytes: LocalNodeStream<Uint8Array>, format: literal)
    open fun setFilename(filename: String)
    open fun getFilename(): String
    open fun writeHeader(): Uint8Array

    companion object {
        var tag: Any
    }
}

external open class SymmetricallyEncryptedDataPacket : BasePacket {
    open fun decrypt(sessionKeyAlgorithm: symmetric, sessionKey: Uint8Array, config: Config = definedExternally)
    open fun encrypt(sessionKeyAlgorithm: symmetric, sessionKey: Uint8Array, config: Config = definedExternally)

    companion object {
        var tag: Any
    }
}

external open class MarkerPacket : BasePacket {
    companion object {
        var tag: Any
    }
}

external open class UserAttributePacket : BasePacket {
    open fun equals(packet: UserAttributePacket): Boolean

    companion object {
        var tag: Any
    }
}

external open class OnePassSignaturePacket : BasePacket {
    open var correspondingSig: Promise<SignaturePacket>
    open var verify: Any

    companion object {
        var tag: Any
    }
}

external open class UserIDPacket : BasePacket {
    open var name: String
    open var comment: String
    open var email: String
    open var userID: String

    companion object {
        var tag: Any
        fun fromObject(userID: UserID): UserIDPacket
    }
}

external interface `T$44` {
    @nativeGetter
    operator fun get(name: String): String?

    @nativeSetter
    operator fun set(name: String, value: String)
}

external open class SignaturePacket : BasePacket {
    open var version: Number
    open var signatureType: signature?
    open var hashAlgorithm: hash?
    open var publicKeyAlgorithm: publicKey?
    open var signatureData: Uint8Array?
    open var unhashedSubpackets: Uint8Array?
    open var signedHashValue: Uint8Array?
    open var created: Date?
    open var signatureExpirationTime: Number?
    open var signatureNeverExpires: Boolean
    open var exportable: Boolean?
    open var trustLevel: Number?
    open var trustAmount: Number?
    open var regularExpression: Number?
    open var revocable: Boolean?
    open var keyExpirationTime: Number?
    open var keyNeverExpires: Boolean?
    open var preferredSymmetricAlgorithms: Array<symmetric>?
    open var revocationKeyClass: Number?
    open var revocationKeyAlgorithm: publicKey?
    open var revocationKeyFingerprint: Uint8Array?
    open var issuerKeyID: KeyID
    open var notation: `T$44`?
    open var preferredHashAlgorithms: Array<hash>?
    open var preferredCompressionAlgorithms: Array<compression>?
    open var keyServerPreferences: Array<Number>?
    open var preferredKeyServer: String?
    open var isPrimaryUserID: Boolean?
    open var policyURI: String?
    open var keyFlags: Uint8Array?
    open var signersUserID: String?
    open var reasonForRevocationFlag: reasonForRevocation?
    open var reasonForRevocationString: String?
    open var features: Uint8Array?
    open var signatureTargetPublicKeyAlgorithm: publicKey?
    open var signatureTargetHashAlgorithm: hash?
    open var signatureTargetHash: String?
    open var embeddedSignature: SignaturePacket?
    open var issuerKeyVersion: Number?
    open var issuerFingerprint: Uint8Array?
    open var preferredAEADAlgorithms: Array<aead>?
    open var revoked: Boolean?
    open fun sign(
        key: SecretKeyPacket,
        data: Uint8Array,
        date: Date = definedExternally,
        detached: Boolean = definedExternally
    ): Promise<Unit>

    open fun sign(key: SecretKeyPacket, data: Uint8Array): Promise<Unit>
    open fun sign(key: SecretKeyPacket, data: Uint8Array, date: Date = definedExternally): Promise<Unit>
    open fun sign(
        key: SecretSubkeyPacket,
        data: Uint8Array,
        date: Date = definedExternally,
        detached: Boolean = definedExternally
    ): Promise<Unit>

    open fun sign(key: SecretSubkeyPacket, data: Uint8Array): Promise<Unit>
    open fun sign(key: SecretSubkeyPacket, data: Uint8Array, date: Date = definedExternally): Promise<Unit>
    open fun verify(
        key: AnyKeyPacket,
        signatureType: signature,
        data: Uint8Array?,
        date: Date = definedExternally,
        detached: Boolean = definedExternally,
        config: Config = definedExternally
    ): Promise<Unit>

    open fun verify(key: AnyKeyPacket, signatureType: signature, data: Uint8Array?): Promise<Unit>
    open fun verify(
        key: AnyKeyPacket,
        signatureType: signature,
        data: Uint8Array?,
        date: Date = definedExternally
    ): Promise<Unit>

    open fun verify(
        key: AnyKeyPacket,
        signatureType: signature,
        data: Uint8Array?,
        date: Date = definedExternally,
        detached: Boolean = definedExternally
    ): Promise<Unit>

    open fun verify(
        key: AnyKeyPacket,
        signatureType: signature,
        data: Any?,
        date: Date = definedExternally,
        detached: Boolean = definedExternally,
        config: Config = definedExternally
    ): Promise<Unit>

    open fun verify(key: AnyKeyPacket, signatureType: signature, data: Any?): Promise<Unit>
    open fun verify(
        key: AnyKeyPacket,
        signatureType: signature,
        data: Any?,
        date: Date = definedExternally
    ): Promise<Unit>

    open fun verify(
        key: AnyKeyPacket,
        signatureType: signature,
        data: Any?,
        date: Date = definedExternally,
        detached: Boolean = definedExternally
    ): Promise<Unit>

    open fun isExpired(date: Date = definedExternally): Boolean
    open fun getExpirationTime(): dynamic /* Date | Any */

    companion object {
        var tag: Any
    }
}

external open class TrustPacket : BasePacket {
    companion object {
        var tag: Any
    }
}

external open class UnparseablePacket {
    open var tag: packet
    open var write: () -> Uint8Array
}


external interface GenericWebStream<T>
external interface GenericNodeStream<T>

external interface LocalWebStream<T> : GenericWebStream<T>

external interface LocalNodeStream<T> : GenericNodeStream<T>

external interface UserID {
    var name: String?
        get() = definedExternally
        set(value) = definedExternally
    var email: String?
        get() = definedExternally
        set(value) = definedExternally
    var comment: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface SessionKey {
    var data: Uint8Array
    var algorithm: String /* "plaintext" | "idea" | "tripledes" | "cast5" | "blowfish" | "aes128" | "aes192" | "aes256" | "twofish" */
    var aeadAlgorithm: String? /* "eax" | "ocb" | "gcm" */
        get() = definedExternally
        set(value) = definedExternally
}

external interface ReasonForRevocation {
    var flag: reasonForRevocation?
        get() = definedExternally
        set(value) = definedExternally
    var string: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface EncryptOptions {
    var message: Message<dynamic /* Uint8Array | String | WebStream<dynamic /* Uint8Array | String */> | NodeStream<dynamic /* Uint8Array | String */> */>
    var encryptionKeys: dynamic /* PublicKey? | Array<PublicKey>? */
        get() = definedExternally
        set(value) = definedExternally
    var signingKeys: dynamic /* PrivateKey? | Array<PrivateKey>? */
        get() = definedExternally
        set(value) = definedExternally
    var passwords: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var sessionKey: SessionKey?
        get() = definedExternally
        set(value) = definedExternally
    var format: String? /* "armored" | "binary" | "object" */
        get() = definedExternally
        set(value) = definedExternally
    var signature: Signature?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var wildcard: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var signingKeyIDs: dynamic /* KeyID? | Array<KeyID>? */
        get() = definedExternally
        set(value) = definedExternally
    var encryptionKeyIDs: dynamic /* KeyID? | Array<KeyID>? */
        get() = definedExternally
        set(value) = definedExternally
    var signingUserIDs: dynamic /* UserID? | Array<UserID>? */
        get() = definedExternally
        set(value) = definedExternally
    var encryptionUserIDs: dynamic /* UserID? | Array<UserID>? */
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DecryptOptions {
    var message: Message<dynamic /* Uint8Array | String | WebStream<dynamic /* Uint8Array | String */> | NodeStream<dynamic /* Uint8Array | String */> */>
    var decryptionKeys: dynamic /* PrivateKey? | Array<PrivateKey>? */
        get() = definedExternally
        set(value) = definedExternally
    var passwords: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var sessionKeys: dynamic /* SessionKey? | Array<SessionKey>? */
        get() = definedExternally
        set(value) = definedExternally
    var verificationKeys: dynamic /* PublicKey? | Array<PublicKey>? */
        get() = definedExternally
        set(value) = definedExternally
    var expectSigned: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var format: String? /* "utf8" | "binary" */
        get() = definedExternally
        set(value) = definedExternally
    var signature: Signature?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external interface SignOptions {
    var message: dynamic /* CleartextMessage | Message<dynamic /* Uint8Array | String | WebStream<dynamic /* Uint8Array | String */> | NodeStream<dynamic /* Uint8Array | String */> */> */
        get() = definedExternally
        set(value) = definedExternally
    var signingKeys: dynamic /* PrivateKey | Array<PrivateKey> */
        get() = definedExternally
        set(value) = definedExternally
    var format: String? /* "armored" | "binary" | "object" */
        get() = definedExternally
        set(value) = definedExternally
    var detached: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var signingKeyIDs: dynamic /* KeyID? | Array<KeyID>? */
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var signingUserIDs: dynamic /* UserID? | Array<UserID>? */
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external interface VerifyOptions {
    var message: dynamic /* CleartextMessage | Message<dynamic /* Uint8Array | String | WebStream<dynamic /* Uint8Array | String */> | NodeStream<dynamic /* Uint8Array | String */> */> */
        get() = definedExternally
        set(value) = definedExternally
    var verificationKeys: dynamic /* PublicKey | Array<PublicKey> */
        get() = definedExternally
        set(value) = definedExternally
    var expectSigned: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var format: String? /* "utf8" | "binary" */
        get() = definedExternally
        set(value) = definedExternally
    var signature: Signature?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external interface EncryptSessionKeyOptions : SessionKey {
    var encryptionKeys: dynamic /* PublicKey? | Array<PublicKey>? */
        get() = definedExternally
        set(value) = definedExternally
    var passwords: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var format: String? /* "armored" | "binary" | "object" */
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var wildcard: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var encryptionKeyIDs: dynamic /* KeyID? | Array<KeyID>? */
        get() = definedExternally
        set(value) = definedExternally
    var encryptionUserIDs: dynamic /* UserID? | Array<UserID>? */
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external interface SerializedKeyPair<T> {
    var privateKey: T
    var publicKey: T
}

external interface KeyPair {
    var privateKey: PrivateKey
    var publicKey: PublicKey
}

external interface GenerateKeyOptions {
    var userIDs: dynamic /* UserID | Array<UserID> */
        get() = definedExternally
        set(value) = definedExternally
    var passphrase: String?
        get() = definedExternally
        set(value) = definedExternally
    var type: String? /* "ecc" | "rsa" */
        get() = definedExternally
        set(value) = definedExternally

    @JsName("curve")
    var curve: String? /* "ed25519" | "curve25519" | "p256" | "p384" | "p521" | "secp256k1" | "brainpoolP256r1" | "brainpoolP384r1" | "brainpoolP512r1" */
        get() = definedExternally
        set(value) = definedExternally
    var rsaBits: Number?
        get() = definedExternally
        set(value) = definedExternally
    var keyExpirationTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var subkeys: Array<SubkeyOptions>?
        get() = definedExternally
        set(value) = definedExternally
    var format: String? /* "armored" | "object" | "binary" */
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}



external interface SubkeyOptions {
    var type: String? /* "ecc" | "rsa" */
        get() = definedExternally
        set(value) = definedExternally
    var curve: String? /* "ed25519" | "curve25519" | "p256" | "p384" | "p521" | "secp256k1" | "brainpoolP256r1" | "brainpoolP384r1" | "brainpoolP512r1" */
        get() = definedExternally
        set(value) = definedExternally
    var rsaBits: Number?
        get() = definedExternally
        set(value) = definedExternally
    var keyExpirationTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var date: Date?
        get() = definedExternally
        set(value) = definedExternally
    var sign: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var config: PartialConfig?
        get() = definedExternally
        set(value) = definedExternally
}

external open class KeyID {
    open var bytes: String
    open fun equals(keyID: KeyID, matchWildcard: Boolean = definedExternally): Boolean
    open fun toHex(): String

    companion object {
        fun fromID(hex: String): KeyID
    }
}

external interface DecryptMessageResult {
    var data: dynamic /* Uint8Array | String | WebStream<dynamic /* Uint8Array | String */> | NodeStream<dynamic /* Uint8Array | String */> */
        get() = definedExternally
        set(value) = definedExternally
    var signatures: Array<VerificationResult>
    var filename: String
}

external interface VerifyMessageResult {
    var data: dynamic /* Uint8Array | String | WebStream<dynamic /* Uint8Array | String */> | NodeStream<dynamic /* Uint8Array | String */> */
        get() = definedExternally
        set(value) = definedExternally
    var signatures: Array<VerificationResult>
}

external fun armor(
    messagetype: armor,
    body: Any?,
    partindex: Number,
    parttotal: Number,
    config: Config = definedExternally
): String

external interface `T$45` {
    var text: String
    var data: dynamic /* WebStream<Uint8Array> | NodeStream<Uint8Array> */
        get() = definedExternally
        set(value) = definedExternally
    var type: armor
}

external fun unarmor(input: String, config: Config = definedExternally): Promise<`T$45`>