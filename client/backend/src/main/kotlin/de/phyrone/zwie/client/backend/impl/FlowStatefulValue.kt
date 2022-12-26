package de.phyrone.zwie.client.backend.impl

import de.phyrone.zwie.client.backend.Disposable
import de.phyrone.zwie.client.backend.StatefulValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.promise
import kotlin.js.Promise

private val scope = CoroutineScope(Dispatchers.Default)

class FlowStatefulValue<T>(
    private val flow: StateFlow<T>,
) : StatefulValue<T> {
    override val value: T
        get() = flow.value

    override fun waitUntil(predicate: (T) -> Boolean): Promise<T> = scope.promise {
        flow.first { predicate(it) }
    }


    override fun addListener(listener: (T) -> Unit): Disposable {
        val job = scope.launch { flow.collect { listener(it) } }
        return object : Disposable {
            override fun dispose() {
                job.cancel()
            }
        }
    }

}