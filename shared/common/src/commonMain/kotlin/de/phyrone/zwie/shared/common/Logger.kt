package de.phyrone.zwie.shared.common

import kotlin.reflect.KClass

expect class Logger  {
    fun info(message: String, vararg args: Any)
    fun warn(message: String, vararg args: Any)
    fun error(message: String, vararg args: Any)
    fun debug(message: String, vararg args: Any)
}

