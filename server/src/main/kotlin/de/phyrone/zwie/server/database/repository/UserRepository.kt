package de.phyrone.zwie.server.database.repository

import de.phyrone.zwie.server.database.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long> {
    fun findByLastDisplayName(displayName: String): UserEntity?

    fun findByLastDisplayNameIgnoreCase(displayName: String): UserEntity?

}