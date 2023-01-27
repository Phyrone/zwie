@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")

import kotlin.js.*
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*
import tsstdlib.Map

external fun beforeUpdate(fn: () -> Any)

external fun onMount(fn: () -> Any)

external fun afterUpdate(fn: () -> Any)

external fun onDestroy(fn: () -> Any)

external interface DispatchOptions {
    var cancelable: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external fun <EventMap : Any> createEventDispatcher(): (type: EventKey, detail: Any, options: DispatchOptions) -> Boolean

external fun <T> setContext(key: Any, context: T): T

external fun <T> getContext(key: Any): T

external fun <T : Map<Any, Any>> getAllContexts(): T

external fun hasContext(key: Any): Boolean