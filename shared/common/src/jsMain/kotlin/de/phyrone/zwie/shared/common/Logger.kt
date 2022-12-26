package de.phyrone.zwie.shared.common

import kotlin.reflect.KClass

actual class Logger(val kClass: KClass<*>) {
    actual fun info(message: String, vararg args: Any) {
        console.log("[${kClass.simpleName}] $message", *args)
    }

    actual fun warn(message: String, vararg args: Any) {
        console.warn("[${kClass.simpleName}] $message", *args)
    }

    actual fun error(message: String, vararg args: Any) {
        console.error("[${kClass.simpleName}] $message", *args)
    }

    actual fun debug(message: String, vararg args: Any) {
        console.asDynamic().debug("[${kClass.simpleName}] $message", args)
    }

}