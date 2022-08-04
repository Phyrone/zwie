package de.phyrone.zwie.server

import com.google.common.flogger.FluentLogger
import com.google.common.flogger.backend.LoggerBackend
import com.google.common.flogger.backend.Platform
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName


private val floggerConsturctor by lazy {
    FluentLogger::class.java.getDeclaredConstructor(LoggerBackend::class.java).also {
        it.isAccessible = true
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun logger(): FluentLogger = FluentLogger.forEnclosingClass()
fun logger(clazz: KClass<*>) = floggerConsturctor.newInstance(Platform.getBackend(clazz.qualifiedName ?: clazz.jvmName))
fun logger(clazz: Class<*>) = floggerConsturctor.newInstance(Platform.getBackend(clazz.name))
fun logger(name: String) = floggerConsturctor.newInstance(Platform.getBackend(name))

