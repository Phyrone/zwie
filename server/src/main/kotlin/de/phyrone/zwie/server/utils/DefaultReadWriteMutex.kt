package de.phyrone.zwie.server.utils

import java.util.concurrent.LinkedBlockingQueue


//TODO implement
class DefaultReadWriteMutex : ReadWriteMutex {

    override val isReadLocked: Boolean
        get() = readLocks.isNotEmpty()

    override var isWriteLocked: Boolean = false
        private set

    override val isWriteLockPending: Boolean
        get() = pendingWriteLocks.any { !it.patient }


    private val sync = Any()
    private val readLocks = mutableSetOf<ReadLockImpl>()


    private val pendingReadLocks = LinkedBlockingQueue<ReadLockImpl>()
    private val pendingWriteLocks = LinkedBlockingQueue<WriteLockImpl>()


    private class ReadLockImpl() : ReadWriteMutex.Lock {

        override suspend fun unlock() {
            TODO("Not yet implemented")
        }

    }

    private class WriteLockImpl(val patient: Boolean) : ReadWriteMutex.Lock {

        override suspend fun unlock() {
            TODO("Not yet implemented")
        }

    }

    override fun tryLockRead(): ReadWriteMutex.Lock? {
        TODO("Not yet implemented")
    }

    override fun tryLockWrite(): ReadWriteMutex.Lock? {
        TODO("Not yet implemented")
    }

    override suspend fun lockRead(): ReadWriteMutex.Lock {
        TODO("Not yet implemented")
    }

    override suspend fun lockWrite(patient: Boolean): ReadWriteMutex.Lock {
        TODO("Not yet implemented")
    }
}