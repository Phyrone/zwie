package de.phyrone.zwie.server.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

@IndexTable
object UsersTable : UUIDTable("user") {
    val token = text("token").uniqueIndex()
    val nickname = varchar("nickname", 64)
    val firstSeen = datetime("first_seen").clientDefault { LocalDateTime.now() }
    val lastSeen = datetime("last_seen")
}