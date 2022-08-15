package de.phyrone.zwie.server.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable

@IndexTable
object ChannelsTable : UUIDTable("channel") {
    var name = varchar("name", 64)
    var type = varchar("type", 16)
    var order = integer("order").default(0)
    var parent = reference("parent", ChannelsTable).nullable().default(null)
}