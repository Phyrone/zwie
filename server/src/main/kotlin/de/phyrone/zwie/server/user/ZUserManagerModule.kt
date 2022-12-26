package de.phyrone.zwie.server.user

import com.github.benmanes.caffeine.cache.Caffeine
import de.phyrone.zwie.server.database.entity.UserEntity
import de.phyrone.zwie.server.database.tables.UsersTable
import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.utils.logger
import io.ktor.network.sockets.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeout
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionManager
import org.jetbrains.exposed.sql.update
import org.koin.core.KoinApplication
import org.koin.core.component.inject
import org.koin.dsl.bind
import org.koin.dsl.module
import java.sql.Connection
import java.time.LocalDateTime
import java.util.LinkedList
import kotlin.system.measureNanoTime
import kotlin.time.Duration.Companion.seconds


@Module("core::user")
@DependsOn("core::database")
class ZUserManagerModule : ZUserManager, CommonModule {

    private val database by inject<Database>()
    lateinit var pscope: CoroutineScope

    private val usersCache = Caffeine.newBuilder().softValues()
        .build<String, ZUserImpl>(::loadUser)

    private fun loadUser(fingerprint: String): ZUserImpl {
        val ts = LocalDateTime.now()
        val dbEntity = transaction {

            val dbEntity = UserEntity.findById(fingerprint) ?: UserEntity.new(fingerprint) {
                firstSeen = ts
            }
            dbEntity.lastSeen = ts
            dbEntity
        }
        return ZUserImpl(dbEntity)
    }

    override suspend fun getUser(fingerprint: String): ZUser =
        usersCache.get(fingerprint) ?: error("got nullpointer from cache/cacheloader")

    private inner class ZUserImpl(
        override val dbEntity: UserEntity,
    ) : ZUser {


        override val id: String = dbEntity.id.value

        override var name: MutableStateFlow<String> = MutableStateFlow(transaction(
            db = database,
            transactionIsolation = Connection.TRANSACTION_READ_COMMITTED,
            readOnly = true,
            repetitionAttempts = database.transactionManager.defaultRepetitionAttempts
        ) { dbEntity.name })

        override suspend fun isOnline(): Boolean = online.first()

        //online offline can only be changed when sessions altered
        private val sessionsAndOnlineOfflineLock = Mutex()
        private val sessions = mutableSetOf<ZUserSessionImpl>()
        override val online = MutableStateFlow(false)
        private var userUpdateJob: Job? = null

        private suspend fun setOnlineIfRequired() {
            if (!online.value) {
                setOnline()
            }
        }

        private suspend fun setOfflineIfRequired() {
            if (sessions.isEmpty() && online.value) {
                setOffline()
            }
        }

        private suspend fun setOnline() {
            userUpdateJob = runUserLoop()
            online.emit(true)
            logger.atInfo().log("User %s is now online", id)
        }

        private fun runUserLoop() = pscope.launch {
            launch {
                try {
                    while (true) {
                        updateLastSeen()
                        delay(30.seconds)
                    }
                } finally {
                    updateLastSeen()
                }
            }
        }

        private suspend fun setOffline() {
            userUpdateJob?.cancel()
            userUpdateJob = null
            online.emit(false)
            logger.atInfo().log("User %s is now offline", id)
        }

        override suspend fun createSession(sessionData: BackingSessionData): ZUserSession {
            val session = ZUserSessionImpl(sessionData, this)
            sessionsAndOnlineOfflineLock.withLock {
                sessions.add(session)
                setOnlineIfRequired()
            }
            return session
        }

        suspend fun removeSession(session: ZUserSessionImpl) {
            sessionsAndOnlineOfflineLock.withLock {
                sessions.remove(session)
                setOfflineIfRequired()
            }
        }

        override suspend fun getSessions(): Set<ZUserSession> {
            return sessionsAndOnlineOfflineLock.withLock { sessions.toSet() }
        }

        private suspend fun updateLastSeen() {
            newSuspendedTransaction(Dispatchers.IO, database) {
                UsersTable.update({ UsersTable.id eq this@ZUserImpl.id }) {
                    it[lastSeen] = LocalDateTime.now()
                }
                //dbEntity.lastSeen = LocalDateTime.now()
            }
        }


        override suspend fun delete() {
            if (online.first()) error("user is still online")
            newSuspendedTransaction(Dispatchers.IO, database, Connection.TRANSACTION_SERIALIZABLE) {
                dbEntity.delete()
            }
        }
    }

    private inner class ZUserSessionImpl(
        private val backingSessionData: BackingSessionData,
        override val user: ZUserImpl,
    ) : ZUserSession {

        private var pingHistoryLimit = 60
        private val pingHistory = LinkedList<Long>()
        private val sessionJob = pscope.launch {
            launch {
                while (true) {
                    delay(1000)
                    try {
                        val pingTime = withTimeout(30.seconds) {
                            measureNanoTime { backingSessionData.runPingPong() }
                        }
                        pingHistory.push(pingTime)
                    } catch (_: TimeoutCancellationException) {
                    }
                    while (pingHistory.size > pingHistoryLimit) {
                        pingHistory.removeLast()
                    }
                }
            }
        }

        init {
            logger.atFine().log("created session (%s)", user.id)
        }

        override val remoteAddress: Pair<String, Int>
            get() = backingSessionData.remoteAddress
        override val localAddress: Pair<String, Int>
            get() = backingSessionData.localAddress


        override suspend fun closeConnection(message: String?) {
            backingSessionData.closeConnection(message)
        }

        override suspend fun close() {
            sessionJob.cancel()
            user.removeSession(this)
            logger.atFine().log("closed session (%s)", user.id)
        }

    }


    private val koinApplication by inject<KoinApplication>()
    private val koinModule = module {
        single { this@ZUserManagerModule } bind ZUserManager::class
    }

    override suspend fun onEnable() {
        koinApplication.modules(koinModule)
        pscope = CoroutineScope(Dispatchers.Default /* */)

    }

    override suspend fun onDisable() {
        koinApplication.unloadModules(koinModule)
        pscope.cancel()
    }

    companion object {
        private val logger = logger()
    }
}