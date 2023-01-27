@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)

package de.phyrone.zwie.client.backend.import

typealias Subscriber<T> = (value: T) -> Unit

typealias Unsubscriber = () -> Unit

typealias Invalidator<T> = (value: T) -> Unit

@JsNonModule
@JsModule("svelte/store")
external interface Readable<T> {
    fun subscribe(run: Subscriber<T>, invalidate: Invalidator<T>): Unsubscriber

}