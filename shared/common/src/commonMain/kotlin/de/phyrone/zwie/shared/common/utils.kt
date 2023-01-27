package de.phyrone.zwie.shared.common

import kotlinx.coroutines.flow.*

fun <T, C : Iterable<T>> StateFlow<C>.subscribeCollectionUpdates(): Flow<CollectionUpdate<T, C>> = flow {
    var last: C = value
    emit(CollectionUpdate(last, last.map { CollectionUpdateRecord.Add(it) }))
    fun compare(oldOne: C, newOne: C): List<CollectionUpdateRecord> {
        val added = newOne - oldOne
        val removed = oldOne - newOne
        return added.map {
            CollectionUpdateRecord.Add(it)
        } + removed.map {
            CollectionUpdateRecord.Remove(it)
        }
    }
    collect { collection ->
        val diff = compare(last, collection)
        if (diff.isNotEmpty()) {
            emit(CollectionUpdate(collection, diff))
        }
        last = collection
    }
}

data class CollectionUpdate<T, C : Iterable<T>>(
    val snapshot: C,
    val records: List<CollectionUpdateRecord>
) {
    fun added() = records.filterIsInstance<CollectionUpdateRecord.Add<T>>()
    fun addItems() = added().map { it.item }
    fun removeed() = records.filterIsInstance<CollectionUpdateRecord.Remove<T>>()

    fun removedItems() = removeed().map { it.item }
}

sealed interface CollectionUpdateRecord {
    data class Add<T>(val item: T) : CollectionUpdateRecord
    data class Remove<T>(val item: T) : CollectionUpdateRecord

}