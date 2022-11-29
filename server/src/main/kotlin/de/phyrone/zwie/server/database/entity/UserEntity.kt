package de.phyrone.zwie.server.database.entity

import de.phyrone.zwie.server.database.tables.UsersTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<String>) : Entity<String>(id) {

    var name by UsersTable.name

    var firstSeen by UsersTable.firstSeen
    var lastSeen by UsersTable.lastSeen

    companion object : EntityClass<String, UserEntity>(UsersTable)
}