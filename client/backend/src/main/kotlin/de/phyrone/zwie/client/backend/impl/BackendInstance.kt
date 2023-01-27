package de.phyrone.zwie.client.backend.impl

import de.phyrone.zwie.client.backend.EmptyPromise
import de.phyrone.zwie.client.backend.ServerInfo
import de.phyrone.zwie.client.backend.ZwieJsClientBackend
import de.phyrone.zwie.client.backend.import.Readable
import de.phyrone.zwie.shared.protocol.intl.az.AZSocketClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class BackendInstance(

) : ZwieJsClientBackend {
    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        console.log("ZwieJsClientInstanceImpl", "init")
        scope.launch {
            try {
                AZSocketClient("")
            } catch (e: Throwable) {

            }
        }
    }

    override fun destroy() {
        scope.cancel()
    }

    override fun servers(): Readable<Array<ServerInfo>> {

        TODO("Not yet implemented")
    }


}