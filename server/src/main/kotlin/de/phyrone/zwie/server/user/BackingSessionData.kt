package de.phyrone.zwie.server.user

import io.ktor.network.sockets.*
import kotlinx.coroutines.TimeoutCancellationException

interface BackingSessionData {
    val localAddress: Pair<String, Int>
    val remoteAddress: Pair<String, Int>
    suspend fun closeConnection(message: String? = null)

    suspend fun runPingPong()
}