@file:JsExport @file:Suppress("unused")

package de.phyrone.zwie.client.backend

import de.phyrone.zwie.client.backend.import.Readable
import de.phyrone.zwie.shared.BuildConst
import kotlin.js.Promise

@Suppress("NON_EXPORTABLE_TYPE")
typealias EmptyPromise = Promise<Unit>

object ZwieEntry {
    fun version(): String = BuildConst.VERSION
    fun kotlinVersion(): String = BuildConst.KOTLIN_VERSION

    fun createBackend(platformInfo: PlatformInfo): ZwieJsClientBackend {
        TODO()
    }
}

enum class ClientPlatform {
    WEB, TAURI
}

interface ZwieJsClientBackend {

    fun destroy()


    fun servers(): Readable<Array<ServerInfo>>
}

interface ServerInfo {
    val name: String
}

data class PlatformInfo(
    val dev: Boolean, val platform: ClientPlatform
)