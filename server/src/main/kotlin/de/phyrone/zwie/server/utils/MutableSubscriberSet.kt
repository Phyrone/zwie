package de.phyrone.zwie.server.utils

interface MutableSubscriberSet<T> : SubscriberSet<T> {
    suspend fun add(element: T): Boolean
    suspend fun remove(element: T): Boolean
    suspend fun removeIf(predicate: (T) -> Boolean): Set<T>

}