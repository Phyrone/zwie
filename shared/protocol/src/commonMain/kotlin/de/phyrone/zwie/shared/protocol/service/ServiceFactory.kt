package de.phyrone.zwie.shared.protocol.service

import de.phyrone.zwie.shared.protocol.ZSocket

interface ServiceFactory<T> {
    val name: String
    suspend fun create(socket: ZSocket, serviceDef: ZNamespace): T
}