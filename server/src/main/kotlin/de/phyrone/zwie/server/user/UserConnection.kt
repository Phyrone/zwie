package de.phyrone.zwie.server.user

import de.phyrone.zwie.server.data.packets.client.PacketClient
import de.phyrone.zwie.server.data.packets.server.PacketServer
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import java.io.Closeable

interface UserConnection : Closeable {

    val sendChannel: SendChannel<PacketServer>
    val receiveChannel: ReceiveChannel<PacketClient>
}