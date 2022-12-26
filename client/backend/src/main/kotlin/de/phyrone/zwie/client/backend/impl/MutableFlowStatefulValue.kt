package de.phyrone.zwie.client.backend.impl

import de.phyrone.zwie.client.backend.Disposable
import de.phyrone.zwie.client.backend.MutableStatefulValue
import de.phyrone.zwie.client.backend.StatefulValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.promise
import kotlin.js.Promise

private val scope = CoroutineScope(Dispatchers.Default)

class MutableFlowStatefulValue<T>(
    private val flow: MutableStateFlow<T>,
) : MutableStatefulValue<T> {
    override var value: T
        get() = flow.value
        set(value) {
            flow.value = value
        }

    override fun update(update: (T) -> T) = flow.update(update)
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