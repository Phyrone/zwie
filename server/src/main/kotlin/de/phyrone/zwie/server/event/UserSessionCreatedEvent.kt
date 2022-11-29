package de.phyrone.zwie.server.event

import de.phyrone.zwie.server.data.proto01.packets.PacketClient
import de.phyrone.zwie.server.data.proto01.packets.PacketServer
import de.phyrone.zwie.server.vserver.ZUserSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.MutableSharedFlow

data class UserSessionCreatedEven(
    val session: ZUserSession,
    private val tasks: MutableList<suspend CoroutineScope.() -> Unit>,
    val receiveFlow: MutableSharedFlow<PacketClient>,
    val sendFlow : MutableSharedFlow<PacketServer>,
) {

    fun runTask(task: suspend CoroutineScope.() -> Unit) {
        tasks.add(task)
    }

}