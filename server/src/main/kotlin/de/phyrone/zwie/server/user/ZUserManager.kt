package de.phyrone.zwie.server.user

interface ZUserManager {
    suspend fun getUser(fingerprint: String): ZUser

    //suspend fun allSessions(): List<ZUserSession>
    //suspend fun allOnlineUsers(): List<ZUser>
}