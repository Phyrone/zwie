package de.phyrone.musicnova.misc

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.internal.MainDispatcherFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@OptIn(InternalCoroutinesApi::class)
class DefaultServerMainDispatcherFactory : MainDispatcherFactory, KoinComponent {
    override val loadPriority: Int = 0

    private val executor by inject<MainCoroutineDispatcher>()

    override fun createDispatcher(allFactories: List<MainDispatcherFactory>): MainCoroutineDispatcher = executor
}