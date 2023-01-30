package de.phyrone.zwie.client.backend.impl

import de.phyrone.zwie.client.backend.EmptyPromise
import de.phyrone.zwie.client.backend.ServerConnection
import de.phyrone.zwie.shared.protocol.intl.az.AZSocketClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.promise

class ServerConnectionInstance(
    val backendInstance: BackendInstance,
    override val name: String,
    private val client: AZSocketClient
) : ServerConnection {
    private val scope = backendInstance.scope

    override fun disconnect(): EmptyPromise = scope.promise { client.close() }



}