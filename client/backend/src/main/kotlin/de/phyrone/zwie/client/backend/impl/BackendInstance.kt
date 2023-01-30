package de.phyrone.zwie.client.backend.impl

import de.phyrone.zwie.client.backend.PlatformInfo
import de.phyrone.zwie.client.backend.ServerConnection
import de.phyrone.zwie.client.backend.ZwieJsClientBackend
import de.phyrone.zwie.client.backend.data.ClientLocalData
import de.phyrone.zwie.client.backend.import.Readable
import de.phyrone.zwie.client.backend.import.localforage
import de.phyrone.zwie.shared.crypt.gpg.GPG
import de.phyrone.zwie.shared.protocol.intl.az.AZSocketClient
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlin.js.Promise

class BackendInstance(
    val platformInfo: PlatformInfo
) : ZwieJsClientBackend {
    val scope = CoroutineScope(Dispatchers.Default)

    init {
        console.log("BackendInstance", "init")
    }


    private suspend fun backenBootstrap() {
        localforage.ready().await()
        console.log("BackendDatabase Driver=", localforage.driver())

        //val data = localforage.getItem<ClientLocalData>(DATA_SAVE_KEY).await()
        //    ?: ClientLocalData.default.also { localforage.setItem(DATA_SAVE_KEY, it).await() }
        //console.log("BackendDatabase Data=", data)


    }

    init {
        scope.launch {
            try {
                backenBootstrap()
            } catch (e: Throwable) {
                console.error("BackendInstance", "init", e)
            }
        }
    }

    override fun destroy() {
        scope.cancel()
    }

    override fun connect(url: String): Promise<ServerConnection> = scope.promise {
        val client = AZSocketClient(GPG.generateKey(), url)
        val instance = ServerConnectionInstance(this@BackendInstance, "server", client)
        serversStateFlow.update { it + instance }
        return@promise instance
    }

    override fun connectedServers(): Readable<Array<ServerConnection>> = serversStateFlowRead

    override fun getServer(name: String): ServerConnection? = serversStateFlow.value.firstOrNull { it.name == name }

    private val serversStateFlow = MutableStateFlow<Array<ServerConnection>>(emptyArray())
    private val serversStateFlowRead = serversStateFlow.asReadable()


    companion object{
        const val DATA_SAVE_KEY = "zwie::data"
    }
}