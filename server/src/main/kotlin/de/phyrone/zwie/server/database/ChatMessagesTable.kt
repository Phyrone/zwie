package de.phyrone.zwie.server.database

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table.Dual.clientDefault
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object ChatMessagesTable : UUIDTable("chat_message") {
    val message = text("message")
    val sender = reference("sender", UsersTable, ReferenceOption.CASCADE, ReferenceOption.RESTRICT)
    val channel = reference("channel", ChannelsTable, ReferenceOption.CASCADE, ReferenceOption.CASCADE)
    val timestamp = datetime("timestamp").clientDefault { LocalDateTime.now() }
}