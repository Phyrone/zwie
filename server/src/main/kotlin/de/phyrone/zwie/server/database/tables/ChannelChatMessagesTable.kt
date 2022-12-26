package de.phyrone.zwie.server.database.tables

import de.phyrone.zwie.server.database.IndexTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

@IndexTable
object ChannelChatMessagesTable : LongIdTable("channel_chat_messages") {
    val channel =
        reference("channel", ChannelsTable, onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE)
    val user = reference("user", UsersTable)
    val created = datetime("created").clientDefault { LocalDateTime.now() }
    val replaces = reference(
        "replaces",
        ChannelChatMessagesTable,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE,
        "FK_CHANNEL_CHAT_MESSAGES_REPLACES"
    ).nullable().default(null)
        .uniqueIndex("IDX_CHANNEL_CHAT_MESSAGES_REPLACES")

    val message = text("message").nullable()
}