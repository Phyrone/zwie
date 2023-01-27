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
import tsstdlib.Record
import tsstdlib.Map

external interface Fragment {
    var key: String?
    var first: Any?
    var c: () -> Unit
    var l: (nodes: Any) -> Unit
    var h: () -> Unit
    var m: (target: HTMLElement, anchor: Any) -> Unit
    var p: (ctx: Array<Any>, dirty: Array<Number>) -> Unit
    var r: () -> Unit
    var f: () -> Unit
    var a: () -> Unit
    var i: (local: Any) -> Unit
    var o: (local: Any) -> Unit
    var d: (detaching: Number /* 0 | 1 */) -> Unit
}

external interface `T$$` {
    var dirty: Array<Number>
    var ctx: Array<Any>
    var bound: Any
    var update: () -> Unit
    var callbacks: Any
    var after_update: Array<Any>
    var props: Record<String, dynamic /* 0 | String */>
    var fragment: dynamic /* Boolean? | Fragment? */
        get() = definedExternally
        set(value) = definedExternally
    var not_equal: Any
    var before_update: Array<Any>
    var context: Map<Any, Any>
    var on_mount: Array<Any>
    var on_destroy: Array<Any>
    var skip_bound: Boolean
    var on_disconnect: Array<Any>
    var root: dynamic /* Element | ShadowRoot */
        get() = definedExternally
        set(value) = definedExternally
}