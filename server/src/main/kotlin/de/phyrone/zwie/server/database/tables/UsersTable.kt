package de.phyrone.zwie.server.database.tables

import de.phyrone.zwie.server.database.IndexTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.dao.id.UUIDTable

@IndexTable
object UsersTable : UUIDTable("users") {


}