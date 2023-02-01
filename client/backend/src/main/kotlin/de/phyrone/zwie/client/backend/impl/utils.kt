package de.phyrone.zwie.client.backend.impl

import de.phyrone.zwie.client.backend.import.Invalidator
import de.phyrone.zwie.client.backend.import.Readable
import de.phyrone.zwie.client.backend.import.Subscriber
import de.phyrone.zwie.client.backend.import.Unsubscriber
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


@OptIn(ExperimentalContracts::class)
inline fun <T> withConsoleTimer(name: String, block: () -> T): T {
    contract {
        callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
    }
    console.asDynamic().time(name)
    val result: T
    try {
        result = block()
    } finally {
        console.asDynamic().timeEnd(name)
    }
    return result
}

fun <T> Flow<T>.asReadable(): Readable<T> = StateFlowReadable(this)

class StateFlowReadable<T>(
    private val flow: Flow<T>,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) : Readable<T> {

    override fun subscribe(run: Subscriber<T>, invalidate: Invalidator<T>): Unsubscriber {
        val job = scope.launch {
            try {
                flow.collect { newValue -> run(newValue) }
            } finally {
                invalidate(flow.last())
            }
        }
        return { job.cancel() }

    }

}
