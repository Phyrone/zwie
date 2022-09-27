package de.phyrone.zwie.server.channel

import org.springframework.stereotype.Component
import java.lang.ref.Cleaner
import java.util.UUID

@Component
class ChannelManager {
    val cleaner = Cleaner.create()

    operator fun get(uuid: UUID): Channel? {

        TODO()
    }


    val systemChannelLayout: ChannelLayout
        get() = TODO()

    fun newChannelLayout(): ChannelLayout {
        TODO()
    }

    fun newChannel(name: String, persistence: ChannelPersistence = ChannelPersistence.TEMPORARY): Channel {
        TODO()
    }


    inner class ChannelImpl(
        override val uuid: UUID,
        override val name: String,
        override val persistence: ChannelPersistence,
    ) : Channel {

        override suspend fun delete() {
            TODO("Not yet implemented")
        }

    }

    inner class ChannelLayoutImpl(
    ) : ChannelLayout {
        override var channels: List<Channel> = mutableListOf()

    }

}