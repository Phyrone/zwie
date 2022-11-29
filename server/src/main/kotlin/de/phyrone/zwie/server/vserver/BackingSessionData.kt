package de.phyrone.zwie.server.vserver

import io.ktor.network.sockets.*

interface BackingSessionData {
    val localAddress: Pair<String, Int>
    val remoteAddress: Pair<String, Int>
}