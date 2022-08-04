package de.phyrone.zwie.server.database.dao

import de.phyrone.zwie.server.database.tables.ChannelsTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class ChannelEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var name by ChannelsTable.name
    var type by ChannelsTable.type
    var order by ChannelsTable.order
    var parent by ChannelEntity optionalReferencedOn ChannelsTable.parent
    val childs by ChannelEntity optionalReferrersOn ChannelsTable.parent

    companion object : UUIDEntityClass<ChannelEntity>(ChannelsTable)
}