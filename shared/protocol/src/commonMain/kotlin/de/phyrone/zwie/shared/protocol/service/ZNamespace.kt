package de.phyrone.zwie.shared.protocol.service

import de.phyrone.zwie.shared.protocol.rpc.CallInterface

interface ZNamespace : CallInterface {
    val name: String
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): ZNamespace = this
    operator fun getValue(thisRef: Nothing?, property: kotlin.reflect.KProperty<*>): ZNamespace = this

}