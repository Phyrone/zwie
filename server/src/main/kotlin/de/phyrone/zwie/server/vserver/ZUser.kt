package de.phyrone.zwie.server.vserver

import de.phyrone.zwie.server.database.entity.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow

interface ZUser {
    val id: String
    val name: MutableStateFlow<String>
    val dbEntity: UserEntity
    suspend fun getSessions(): Set<ZUserSession>

    suspend fun updateLastSeen()
}