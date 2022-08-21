package de.phyrone.zwie.server.channel

import com.google.common.eventbus.EventBus
import de.phyrone.zwie.server.UniqeEntity
import de.phyrone.zwie.server.user.User
import java.util.UUID

class Channel(
    val name: String,
    override val uuid: UUID = UUID.randomUUID(),
) : UniqeEntity {
    private val voice_users: MutableSet<User> = mutableSetOf()
    private val localEventBus = EventBus()

    fun addUser(user: User) {
        voice_users.add(user)
        localEventBus.post(UserJoinEvent(user))
    }

    fun removeUser(user: User) {
        voice_users.remove(user)
        localEventBus.post(UserLeaveEvent(user))
    }

    fun addEventListener(listener: Any) {
        localEventBus.register(listener)
    }

    fun removeEventListener(listener: Any) {
        localEventBus.unregister(listener)
    }

    fun isUserInChannel(user: User): Boolean {
        return voice_users.contains(user)
    }

    interface Event {
        val channel: Channel
    }

    inner class UserJoinEvent(
        val user: User,
    ) : Event {
        override val channel: Channel = this@Channel

    }

    inner class UserLeaveEvent(
        val user: User,
    ) : Event {
        override val channel: Channel = this@Channel

    }

}