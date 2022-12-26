package de.phyrone.zwie.server.database.entity

import com.fasterxml.jackson.databind.ObjectMapper
import de.phyrone.zwie.server.data.chat.ChatComponent
import de.phyrone.zwie.server.database.tables.ChannelChatMessagesTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class ChannelChatMessageEntity(id: EntityID<Long>) : LongEntity(id), KoinComponent {
    private val mapper by inject<ObjectMapper>(named("core::mapper::json"))


    var channel by ChannelEntity referencedOn ChannelChatMessagesTable.channel

    var created by ChannelChatMessagesTable.created
    var messageJson by ChannelChatMessagesTable.message

    var replaces by ChannelChatMessageEntity optionalReferencedOn ChannelChatMessagesTable.replaces
    val replacedBy by ChannelChatMessageEntity optionalBackReferencedOn ChannelChatMessagesTable.replaces


    fun isDeletion() = messageJson == null

    fun getLatestRevision(): ChannelChatMessageEntity {
        var current = this
        while (true)
            current = current.replacedBy ?: return current

    }

    fun getFirstRevision(): ChannelChatMessageEntity {
        var current = this
        while (true)
            current = current.replaces ?: return current
    }

    fun getRevisions(): List<ChannelChatMessageEntity> = buildList {
        var current: ChannelChatMessageEntity? = getLatestRevision()
        while (current != null) {
            add(current)
            current = current.replaces
        }
    }

    var message: ChatComponent?
        get() = messageJson?.let { mapper.readValue(it, ChatComponent::class.java) }
        set(value) {
            messageJson = value?.let { mapper.writeValueAsString(it) }
        }

    companion object : LongEntityClass<ChannelChatMessageEntity>(ChannelChatMessagesTable)
}