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
import tsstdlib.Partial
import tsstdlib.Extract

typealias Props = Record<String, Any>

external open class SvelteComponentDev(options: ComponentConstructorOptions__0) : SvelteComponent {
    override fun `$set`(props: Props)
    open fun `$on`(event: String, callback: ((event: Any) -> Unit)?): () -> Unit
    override fun `$on`(type: Any, callback: Any): Any
    override fun `$destroy`()
    @nativeGetter
    open operator fun get(accessor: String): Any?
    @nativeSetter
    open operator fun set(accessor: String, value: Any)
    open var `$$prop_def`: Props
    open var `$$events_def`: Any
    open var `$$slot_def`: Any
    open fun `$capture_state`()
    open fun `$inject_state`()
}

external interface ComponentConstructorOptions<Props : Record<String, Any>> {
    var target: dynamic /* Element | ShadowRoot */
        get() = definedExternally
        set(value) = definedExternally
    var anchor: Element?
        get() = definedExternally
        set(value) = definedExternally
    var props: Props?
        get() = definedExternally
        set(value) = definedExternally
    var context: Map<Any, Any>?
        get() = definedExternally
        set(value) = definedExternally
    var hydrate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var intro: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var `$$inline`: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ComponentConstructorOptions__0 : ComponentConstructorOptions<Record<String, Any>>

external open class SvelteComponentTyped<Props : Record<String, Any>, Events : Record<String, Any>, Slots : Record<String, Any>>(options: ComponentConstructorOptions<Props>) : SvelteComponentDev {
    override fun `$set`(props: Partial<Props>)
    open fun <K : Extract<Any, String>> `$on`(type: K, callback: ((e: Any) -> Unit)?): () -> Unit
    override fun `$destroy`()
    @nativeGetter
    override operator fun get(accessor: String): Any?
    @nativeSetter
    override operator fun set(accessor: String, value: Any)
    override var `$$prop_def`: Props
    override var `$$events_def`: Events
    override var `$$slot_def`: Slots
}

typealias ComponentType<Component> = Any

typealias ComponentProps<Component> = Any

typealias ComponentEvents<Component> = Any