package de.phyrone.zwie.server.network

interface UserConnection {

    suspend fun register(id: String, clazz: Class<*>)
    suspend fun unregister(id: String)

    suspend fun send(message: Any)
    suspend fun receive(): Any

}