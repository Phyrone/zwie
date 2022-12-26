package de.phyrone.zwie.server.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class SharedMutex {


    private val lockQueue = MutableStateFlow<List<ILock>>(emptyList())

    suspend fun acquireExclusive(): Lock = acquire(true)

    suspend fun acquireShare(): Lock = acquire(false)

    private suspend fun acquire(exclusive: Boolean): Lock {
        val lock = ILock(exclusive)
        lock.acquire()
        return lock
    }

    private inner class ILock(
        val exclusive: Boolean,
    ) : Lock {

        suspend fun acquire() {
            lockQueue.update { it + this@ILock }

            if (exclusive) {
                lockQueue.first { it.first() === this@ILock}
            } else {
                lockQueue.first { snapShot ->
                    snapShot.none { it.exclusive } || (snapShot.indexOfFirst { it.exclusive } > snapShot.indexOf(this@ILock))
                }
            }
        }

        override fun unlock() {
            lockQueue.update { it - this@ILock }
        }
    }

    sealed interface Lock {
        fun unlock()
    }

    //@OptIn(ExperimentalContracts::class)
    suspend inline fun <T> withExclusive(block: () -> T): T {
        //contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
        val lock = acquireExclusive()
        try {
            return block()
        } finally {
            lock.unlock()
        }
    }

    //@OptIn(ExperimentalContracts::class)
    suspend inline fun <T> withShare(block: () -> T): T {
        //contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
        val lock = acquireShare()
        try {
            return block()
        } finally {
            lock.unlock()
        }
    }
}