package de.phyrone.zwie.server.utils

import kotlinx.coroutines.flow.Flow

interface SubscriberSet<T> {
    suspend fun subscribe(): Flow<CollectionEvent<T>>

    sealed interface CollectionEvent<T> {
        data class Add<T>(val item: T) : CollectionEvent<T>
        data class Remove<T>(val item: T) : CollectionEvent<T>
    }
}