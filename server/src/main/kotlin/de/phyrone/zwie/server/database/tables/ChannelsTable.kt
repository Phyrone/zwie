package de.phyrone.zwie.server.database.tables

import de.phyrone.zwie.server.database.IndexTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.dao.id.UUIDTable

@IndexTable
object ChannelsTable : LongIdTable("channel") {
    val parent = reference("parent", ChannelsTable).nullable()
    val name = varchar("name", 128).index()
    val nameParentIndex = uniqueIndex(parent, name)
    val password = text("password").nullable().default(null)
    val description = text("description").default("")
    val owner = reference("owner", UsersTable).nullable()
}
