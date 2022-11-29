package de.phyrone.zwie.server.utils

interface ReadWriteMutex {

    val isReadLocked: Boolean
    val isWriteLocked: Boolean
    val isWriteLockPending: Boolean

    fun tryLockRead(): Lock?
    fun tryLockWrite(): Lock?

    suspend fun lockRead(): Lock

    suspend fun lockWrite(patient: Boolean = false): Lock

    interface Lock {
        suspend fun unlock()
        operator fun component1() = this::unlock
    }


}


