package de.phyrone.zwie.server.database

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object UsersTable : LongIdTable("user") {
    val nickname = varchar("nickname", 64)
    val firstSeen = datetime("first_seen")
    val lastSeen = datetime("last_seen")
}