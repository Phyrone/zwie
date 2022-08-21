package de.phyrone.zwie.server.database.repository

import de.phyrone.zwie.server.database.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long> {
    fun findByDisplayName(displayName: String): UserEntity?
}