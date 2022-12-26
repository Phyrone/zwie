package de.phyrone.zwie.shared.common

import kotlin.reflect.KClass

actual class Logger constructor(
    private val kClass: KClass<*>,
) {
    //rep
    actual fun info(message: String, vararg args: Any) {
        println("[${kClass.simpleName}] $message")
    }

    actual fun warn(message: String, vararg args: Any) {
        println("[${kClass.simpleName}] $message")
    }

    actual fun error(message: String, vararg args: Any) {
        println("[${kClass.simpleName}] $message")
    }

    actual fun debug(message: String, vararg args: Any) {
        println("[${kClass.simpleName}] $message")
    }

}