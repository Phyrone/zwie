package de.phyrone.zwie.server.database.tables

import de.phyrone.zwie.server.database.IndexTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption

@IndexTable
object ChannelsTable : LongIdTable("channel") {
    val parent = reference(
        "parent",
        refColumn = ChannelsTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE,
        fkName = "FK_CHANNEL_PARENT"
    ).nullable()
    val name = varchar("name", 128)
    val order = integer("order").default(0)
    val password = text("password").nullable().default(null)
    val description = text("description").default("")
    val owner = reference(
        "owner",
        UsersTable,
        onUpdate = ReferenceOption.CASCADE,
        onDelete = ReferenceOption.SET_NULL,
        fkName = "FK_CHANNEL_OWNER"
    ).nullable()

    init {
        uniqueIndex("IDX_CHANNEL_PARENT_NAME", parent, name)
    }
}
