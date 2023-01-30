@file:JsExport @file:Suppress("unused")

package de.phyrone.zwie.client.backend

import de.phyrone.zwie.client.backend.impl.BackendInstance
import de.phyrone.zwie.client.backend.import.Readable
import de.phyrone.zwie.shared.con.BuildConst
import kotlin.js.Promise

@Suppress("NON_EXPORTABLE_TYPE")
typealias EmptyPromise = Promise<Unit>

object ZwieEntry {
    fun version(): String = BuildConst.VERSION
    fun kotlinVersion(): String = BuildConst.KOTLIN_VERSION

    fun createBackend(platformInfo: PlatformInfo): ZwieJsClientBackend = BackendInstance(platformInfo)
}

enum class ClientPlatform {
    WEB, TAURI
}

interface ZwieJsClientBackend {

    fun destroy()


    fun connect(url: String): Promise<ServerConnection>
    fun connectedServers(): Readable<Array<ServerConnection>>

    fun getServer(name: String): ServerConnection?
}


interface ServerConnection {
    val name: String

    fun disconnect(): EmptyPromise
}

data class PlatformInfo(
    val dev: Boolean, val platform: ClientPlatform
)