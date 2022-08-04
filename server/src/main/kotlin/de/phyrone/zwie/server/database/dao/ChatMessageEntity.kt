package de.phyrone.zwie.server.database.dao

import de.phyrone.zwie.server.database.tables.ChatMessagesTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class ChatMessageEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var message by ChatMessagesTable.message
    var time by ChatMessagesTable.timestamp
    var sender by UserEntity referencedOn ChatMessagesTable.sender
    var channel by ChannelEntity referencedOn ChatMessagesTable.channel
    val messages by ChatMessageEntity referrersOn ChatMessagesTable.channel

    companion object : UUIDEntityClass<ChatMessageEntity>(ChatMessagesTable)
}