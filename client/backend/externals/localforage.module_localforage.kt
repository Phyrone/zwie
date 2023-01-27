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

external interface LocalForageDbInstanceOptions {
    var name: String?
        get() = definedExternally
        set(value) = definedExternally
    var storeName: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface LocalForageOptions : LocalForageDbInstanceOptions {
    var driver: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var size: Number?
        get() = definedExternally
        set(value) = definedExternally
    var version: Number?
        get() = definedExternally
        set(value) = definedExternally
    var description: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface LocalForageDbMethodsCore {
    fun <T> getItem(key: String, callback: (err: Any, value: T?) -> Unit = definedExternally): Promise<T?>
    fun <T> setItem(key: String, value: T, callback: (err: Any, value: T) -> Unit = definedExternally): Promise<T>
    fun removeItem(key: String, callback: (err: Any) -> Unit = definedExternally): Promise<Unit>
    fun clear(callback: (err: Any) -> Unit = definedExternally): Promise<Unit>
    fun length(callback: (err: Any, numberOfKeys: Number) -> Unit = definedExternally): Promise<Number>
    fun key(keyIndex: Number, callback: (err: Any, key: String) -> Unit = definedExternally): Promise<String>
    fun keys(callback: (err: Any, keys: Array<String>) -> Unit = definedExternally): Promise<Array<String>>
    fun <T, U> iterate(iteratee: (value: T, key: String, iterationNumber: Number) -> U, callback: (err: Any, result: U) -> Unit = definedExternally): Promise<U>
}

external interface LocalForageDropInstanceFn {
    @nativeInvoke
    operator fun invoke(dbInstanceOptions: LocalForageDbInstanceOptions = definedExternally, callback: (err: Any) -> Unit = definedExternally): Promise<Unit>
}

external interface LocalForageDriverMethodsOptional {
    var dropInstance: LocalForageDropInstanceFn?
        get() = definedExternally
        set(value) = definedExternally
}

external interface LocalForageDbMethodsOptional {
    var dropInstance: LocalForageDropInstanceFn
}

external interface LocalForageDriverDbMethods : LocalForageDbMethodsCore, LocalForageDriverMethodsOptional

external interface LocalForageDriverSupportFunc {
    @nativeInvoke
    operator fun invoke(): Promise<Boolean>
}

external interface LocalForageDriver : LocalForageDriverDbMethods {
    var _driver: String
    fun _initStorage(options: LocalForageOptions)
    var _support: dynamic /* Boolean? | LocalForageDriverSupportFunc? */
        get() = definedExternally
        set(value) = definedExternally
}

external interface LocalForageSerializer {
    fun <T> serialize(value: T, callback: (value: String, error: Any) -> Unit)
    fun serialize(value: ArrayBuffer, callback: (value: String, error: Any) -> Unit)
    fun serialize(value: Blob, callback: (value: String, error: Any) -> Unit)
    fun deserialize(value: String): dynamic /* T | ArrayBuffer | Blob */
    fun stringToBuffer(serializedString: String): ArrayBuffer
    fun bufferToString(buffer: ArrayBuffer): String
}

external interface LocalForageDbMethods : LocalForageDbMethodsCore, LocalForageDbMethodsOptional

external interface LocalForage : LocalForageDbMethods {
    var LOCALSTORAGE: String
    var WEBSQL: String
    var INDEXEDDB: String
    fun config(options: LocalForageOptions): Boolean
    fun config(options: String): Any
    fun config(): LocalForageOptions
    fun createInstance(options: LocalForageOptions): LocalForage
    fun driver(): String
    fun setDriver(driver: String, callback: () -> Unit = definedExternally, errorCallback: (error: Any) -> Unit = definedExternally): Promise<Unit>
    fun setDriver(driver: String): Promise<Unit>
    fun setDriver(driver: String, callback: () -> Unit = definedExternally): Promise<Unit>
    fun setDriver(driver: Array<String>, callback: () -> Unit = definedExternally, errorCallback: (error: Any) -> Unit = definedExternally): Promise<Unit>
    fun setDriver(driver: Array<String>): Promise<Unit>
    fun setDriver(driver: Array<String>, callback: () -> Unit = definedExternally): Promise<Unit>
    fun defineDriver(driver: LocalForageDriver, callback: () -> Unit = definedExternally, errorCallback: (error: Any) -> Unit = definedExternally): Promise<Unit>
    fun getDriver(driver: String): Promise<LocalForageDriver>
    fun getSerializer(callback: (serializer: LocalForageSerializer) -> Unit = definedExternally): Promise<LocalForageSerializer>
    fun supports(driverName: String): Boolean
    fun ready(callback: (error: Any) -> Unit = definedExternally): Promise<Unit>
}