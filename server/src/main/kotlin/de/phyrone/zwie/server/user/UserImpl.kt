package de.phyrone.zwie.server.user

import de.phyrone.zwie.server.channel.ChannelLayout
import de.phyrone.zwie.server.data.packets.server.PacketServerThisUserKick
import java.util.*

class UserImpl(
    private val connection: UserConnection,
) : User {

    override val uuid: UUID
        get() = TODO("Not yet implemented")


    override suspend fun setChannelLayout(layout: ChannelLayout?) {

        TODO("Not yet implemented")
    }

    override suspend fun resetChannelLayout() {
        TODO("Not yet implemented")
    }

    override suspend fun kick(reason: String?) {
        connection.sendChannel.send(PacketServerThisUserKick(reason))
        close()
        TODO()
    }

    override fun close() {
        connection.close()
    }

}