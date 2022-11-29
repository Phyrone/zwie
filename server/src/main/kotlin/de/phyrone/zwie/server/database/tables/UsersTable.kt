package de.phyrone.zwie.server.database.tables

import de.phyrone.zwie.server.database.IndexTable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

@IndexTable
object UsersTable : IdTable<String>("user") {
    override val id: Column<EntityID<String>> = varchar("fingerprint", 128).entityId()

    val name = varchar("username", 255).default("")
    val firstSeen = datetime("first_seen").clientDefault { LocalDateTime.now() }

    //set when client joined or left and every 30 seconds while client is connected
    val lastSeen = datetime("last_seen").clientDefault { LocalDateTime.now() }

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}