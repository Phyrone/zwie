package de.phyrone.zwie.server.user

import de.phyrone.zwie.server.channel.ChannelLayout
import de.phyrone.zwie.server.data.packets.server.PacketServerThisUserKick
import de.phyrone.zwie.server.database.entity.UserEntity
import java.time.LocalDateTime
import java.util.*

class UserImpl(
    private val connection: UserConnection,
    private var entity: UserEntity,
) : User {

    override val uuid: UUID
        get() = entity.id

    override suspend fun ban(reason: String?, until: LocalDateTime?) {
        TODO("Not yet implemented")
    }

    override suspend fun unban() {
        TODO("Not yet implemented")
    }


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