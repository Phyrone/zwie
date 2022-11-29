package de.phyrone.zwie.server.vserver

import de.phyrone.zwie.server.utils.SuspendClosable
import io.ktor.network.sockets.*
import java.io.Closeable


interface ZUserSession : SuspendClosable {
    val user: ZUser
    val remoteAddress: Pair<String,Int>
    val localAddress: Pair<String,Int>
}