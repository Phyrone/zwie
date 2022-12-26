package de.phyrone.zwie.server.event

import de.phyrone.zwie.server.data.proto01.packets.PacketClient
import de.phyrone.zwie.server.data.proto01.packets.PacketServer
import de.phyrone.zwie.server.user.ZUserSession
import de.phyrone.zwie.server.web.proto01.Protocol01Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.MutableSharedFlow

data class Proto01SessionSetupEvent(
    val socket: Protocol01Socket,
    val session: ZUserSession,
    private val tasks: SendChannel<suspend CoroutineScope.() -> Unit>,
    val receiveFlow: MutableSharedFlow<PacketClient>,
    val send: suspend (PacketServer) -> Unit,
) {

    fun runTask(task: suspend CoroutineScope.() -> Unit) {
        tasks.trySend(task).getOrThrow()
    }

}