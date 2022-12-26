package de.phyrone.zwie.server.user

import de.phyrone.zwie.server.database.entity.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


//probably not online
interface ZUser {
    val id: String
    val name: MutableStateFlow<String>
    val dbEntity: UserEntity
    val online:StateFlow<Boolean>

    suspend fun isOnline(): Boolean


    suspend fun getSessions(): Set<ZUserSession>

    //suspend fun updateLastSeen()

    suspend fun createSession(sessionData: BackingSessionData): ZUserSession
    suspend fun delete()
}