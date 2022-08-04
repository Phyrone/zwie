package de.phyrone.zwie.server.database.dao

import de.phyrone.zwie.server.database.tables.UsersTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var nickname by UsersTable.nickname
    var token by UsersTable.token
    var lastSeen by UsersTable.lastSeen
    var firstSeen by UsersTable.firstSeen

    companion object : UUIDEntityClass<UserEntity>(UsersTable)
}