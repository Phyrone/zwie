package de.phyrone.zwie.client.backend.impl

import de.phyrone.zwie.client.backend.PlatformInfo
import de.phyrone.zwie.client.backend.ServerInfo
import de.phyrone.zwie.client.backend.ZwieJsClientBackend
import de.phyrone.zwie.client.backend.import.Readable
import de.phyrone.zwie.client.backend.import.localforage
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class BackendInstance(
    val platformInfo: PlatformInfo
) : ZwieJsClientBackend {
    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        console.log("BackendInstance", "init")
    }


    private suspend fun backenBootstrap() {
        localforage.ready().await()
        console.log("BackendDatabase Driver=", localforage.driver())

        while (true) {
            delay(1000)
            serversStateFlow.update {
                it + object : ServerInfo {
                    override val name: String
                        get() = "Server ${it.size}"
                }
            }
        }
    }

    init {
        scope.launch { backenBootstrap() }
    }

    override fun destroy() {
        scope.cancel()
    }

    private val serversStateFlow = MutableStateFlow<Array<ServerInfo>>(emptyArray())
    private val serversStateFlowRead = serversStateFlow.asReadable()
    override fun servers(): Readable<Array<ServerInfo>> = serversStateFlowRead


}