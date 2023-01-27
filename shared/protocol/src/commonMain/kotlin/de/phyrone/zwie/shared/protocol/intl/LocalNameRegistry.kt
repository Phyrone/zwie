package de.phyrone.zwie.shared.protocol.intl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class LocalNameRegistry<T>(
    val sendUpdate: suspend ((String?, String, ULong) -> Unit),
) {

    private val counter = MutableStateFlow<ULong>(0u)
    private fun nextID() = counter.updateAndGet { it + 1u }

    private val lock = Mutex()
    private val names = mutableSetOf<Pair<String?, String>>()

    private val values = mutableMapOf<ULong, T>()
    suspend fun getValue(id: ULong): T? = lock.withLock { values[id] }

    suspend fun register(namespace: String?, name: String, value: T): ULong {
        return lock.withLock {
            require(!names.contains(Pair(namespace, name))) { "name is aleready in use" }
            val id = nextID()
            sendUpdate(namespace, name, id)
            names.add(Pair(namespace, name))
            values[id] = value
            return@withLock id
        }
    }

}