package de.phyrone.zwie.server.user

import de.phyrone.zwie.server.utils.SuspendClosable


interface ZUserSession : SuspendClosable {
    val user: ZUser
    val remoteAddress: Pair<String, Int>
    val localAddress: Pair<String, Int>

    suspend fun closeConnection(message: String? = null)
}