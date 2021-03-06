package de.phyrone.zwie.server.database

import de.phyrone.zwie.shared.ChannelType
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.dao.id.UUIDTable

object ChannelsTable : UUIDTable("channel") {
    val name = varchar("name", 64)
    val type = enumeration<ChannelType>("type")
    val order = integer("order").default(0)
    val parent = reference("parent", ChannelsTable).nullable().default(null)


}