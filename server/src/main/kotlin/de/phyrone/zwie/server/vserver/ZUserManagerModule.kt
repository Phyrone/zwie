package de.phyrone.zwie.server.vserver

import de.phyrone.zwie.server.database.entity.UserEntity
import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.utils.SharedMutex
import io.ktor.network.sockets.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.KoinApplication
import org.koin.core.component.inject
import org.koin.dsl.bind
import org.koin.dsl.module
import java.time.LocalDateTime

@Module("core::user")
class ZUserManagerModule : ZUserManager, CommonModule {

    private val database by inject<Database>()

    override suspend fun addUser(userEntity: UserEntity, session: BackingSessionData): ZUserSession {
        usersLock.withExclusive {
            return users.getOrPut(userEntity.id.value) { ZUserImpl(userEntity) }.addSession(session)
        }
    }

    private suspend fun removeUser(user: ZUserImpl) {
        usersLock.withExclusive {
            users.remove(user.id)
        }
    }

    override suspend fun getUser(fingerprint: String): ZUser? = usersLock.withShare { users[fingerprint] }

    private val usersLock = SharedMutex()
    private val users = mutableMapOf<String, ZUserImpl>()

    private inner class ZUserImpl(
        override val dbEntity: UserEntity,
    ) : ZUser {
        override val id: String = dbEntity.id.value
        override lateinit var name: MutableStateFlow<String>

        private val sessionLock = SharedMutex()
        private val sessions = mutableSetOf<ZUserSessionImpl>()

        init {
            transaction(database) {
                name = MutableStateFlow(dbEntity.name)

            }
        }

        suspend fun addSession(session: BackingSessionData): ZUserSession {
            val sessionImpl = ZUserSessionImpl(session, this)
            sessionLock.withExclusive {
                sessions.add(sessionImpl)
            }
            return sessionImpl
        }

        suspend fun removeSession(session: ZUserSessionImpl) {
            sessionLock.withExclusive {
                sessions.remove(session)
                if (sessions.isEmpty()) {
                    removeUser(this)
                }
            }
        }

        override suspend fun getSessions(): Set<ZUserSession> {
            sessionLock.withShare {
                return sessions.toSet()
            }
        }

        override suspend fun updateLastSeen() {
            newSuspendedTransaction (Dispatchers.IO,database){
                dbEntity.lastSeen = LocalDateTime.now()
            }
        }

    }

    private inner class ZUserSessionImpl(
        private val backingSessionData: BackingSessionData,
        override val user: ZUserImpl,
    ) : ZUserSession {


        override val remoteAddress = backingSessionData.remoteAddress
        override val localAddress = backingSessionData.localAddress

        override suspend fun close() {
            user.removeSession(this)
        }
    }


    override suspend fun allSessions(): List<ZUserSession> {
        usersLock.withShare {
            return users.values.flatMap { it.getSessions() }
        }
    }

    override suspend fun allConnectedUsers(): List<ZUser> {
        usersLock.withShare {
            return users.values.toList()
        }
    }

    private val koinApplication by inject<KoinApplication>()
    private val koinModule = module {
        single { this@ZUserManagerModule } bind ZUserManager::class
    }

    override suspend fun onEnable() {
        koinApplication.modules(koinModule)
    }

    override suspend fun onDisable() {
        koinApplication.unloadModules(koinModule)
    }
}