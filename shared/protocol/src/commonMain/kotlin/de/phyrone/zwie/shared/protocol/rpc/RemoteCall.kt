package de.phyrone.zwie.shared.protocol.rpc

import kotlin.properties.ReadOnlyProperty

interface RemoteCall<Req, Res> {

    suspend operator fun invoke(request: Req): Res
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>) = this
    operator fun getValue(thisRef: Nothing?, property: kotlin.reflect.KProperty<*>) = this
}