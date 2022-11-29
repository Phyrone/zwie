package de.phyrone.zwie.server.misc

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID

open class StringEntity(id: EntityID<String>) : Entity<String>(id)
