package de.phyrone.zwie.server.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import de.phyrone.zwie.server.utils.SubscriberSet.CollectionEvent

class DefaultMutableEventfultSet<T>(
    private val snapshot: MutableSet<T> = mutableSetOf(),
) : MutableSubscriberSet<T> {
    private val lock = SharedMutex()
    private val updateFlow = MutableSharedFlow<CollectionEvent<T>>()

    override suspend fun subscribe(): Flow<CollectionEvent<T>> {
        return flow {
            val sharedFlow = lock.withShare {
                snapshot.forEach { emit(CollectionEvent.Add(it)) }
                updateFlow.asSharedFlow()
            }
            sharedFlow.collect { emit(it) }
        }

    }

    override suspend fun add(element: T): Boolean {
        lock.withExclusive {
            if (snapshot.add(element)) {
                updateFlow.emit(CollectionEvent.Add(element))
                return true
            }
            return false
        }
    }

    override suspend fun remove(element: T): Boolean {
        lock.withExclusive {
            if (snapshot.remove(element)) {
                updateFlow.emit(CollectionEvent.Remove(element))
                return true
            }
            return false
        }
    }

    override suspend fun removeIf(predicate: (T) -> Boolean): Set<T> {
        lock.withExclusive {
            val removed = snapshot.filter(predicate)
            if (removed.isNotEmpty()) {
                snapshot.removeAll(removed.toSet())
                updateFlow.emitAll(removed.map { CollectionEvent.Remove(it) }.asFlow())
            }
            return removed.toSet()
        }
    }

    suspend fun clear() {
        lock.withExclusive {
            val removed = snapshot.toSet()
            if (removed.isNotEmpty()) {
                snapshot.clear()
                updateFlow.emitAll(removed.map { CollectionEvent.Remove(it) }.asFlow())
            }
        }
    }


}