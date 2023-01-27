package de.phyrone.zwie.shared.protocol.intl

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RemoteNameRegistry(
    val limit: Int? = null
) {
    private val lock = Mutex()

    private val entries = mutableMapOf<Pair<String?, String>, ULong>()



    suspend fun register(namespace: String?, name: String, id: ULong) {
        lock.withLock {
            if (limit != null && limit <= entries.count())
                error("limit reached")

            val pair = Pair(namespace, name)
            require(!entries.containsKey(pair)) { "already registered" }
            entries.put(pair, id)
        }
    }

    private val names = mutableSetOf<Pair<String?, String>>()


    suspend fun register(namespace: String?, name: String): ULong {
        return lock.withLock {
            val pair = Pair(namespace, name)
            require(!names.contains(pair)) { "name is already registered" }
            names.add(pair)
            return@withLock entries[pair] ?: throw IllegalArgumentException()
        }
    }

}