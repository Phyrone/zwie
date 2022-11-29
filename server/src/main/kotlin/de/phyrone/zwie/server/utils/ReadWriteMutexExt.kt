@file:OptIn(ExperimentalContracts::class)

package de.phyrone.zwie.server.utils

import kotlin.contracts.InvocationKind
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


suspend inline fun <T> ReadWriteMutex.withReadLock(block: suspend () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val lock = lockRead()
    try {
        return block()
    } finally {
        lock.unlock()
    }
}


suspend inline fun <T> ReadWriteMutex.withWriteLock(patient: Boolean = false, block: suspend () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val lock = lockWrite(patient)
    try {
        return block()
    } finally {
        lock.unlock()
    }
}