package de.phyrone.zwie.server.vserver

import de.phyrone.zwie.server.database.entity.UserEntity

interface ZUserManager {
    suspend fun addUser(userEntity: UserEntity, session: BackingSessionData): ZUserSession

    suspend fun getUser(fingerprint: String): ZUser?

    suspend fun allSessions(): List<ZUserSession>
    suspend fun allConnectedUsers(): List<ZUser>
}