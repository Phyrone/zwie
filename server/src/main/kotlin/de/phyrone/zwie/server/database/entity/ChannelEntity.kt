package de.phyrone.zwie.server.database.entity

import de.phyrone.zwie.server.database.tables.ChannelsTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ChannelEntity(id: EntityID<Long>) : LongEntity(id) {
    var parent by ChannelEntity optionalReferencedOn ChannelsTable.parent
    var name by ChannelsTable.name
    var order by ChannelsTable.order
    var passwordHash by ChannelsTable.password
    var description by ChannelsTable.description
    var owner by UserEntity optionalReferencedOn ChannelsTable.owner

    val children by ChannelEntity optionalReferrersOn ChannelsTable.parent

    companion object : LongEntityClass<ChannelEntity>(ChannelsTable)
}