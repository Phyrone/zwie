package de.phyrone.zwie.server.misc

import com.github.benmanes.caffeine.cache.Caffeine
import de.phyrone.zwie.server.utils.logger
import kotlin.reflect.KClass

object InstanceLoader {

    private val logger = logger()
    private val instancesCache = Caffeine.newBuilder()
        .weakValues()
        .evictionListener<KClass<*>, Any?> { key, _, cause ->
            logger.atFine().log("Removed Instance of %s because of %s", key, cause)
        }
        .build<KClass<*>, Any?> { createInstance(it) }


    private fun createInstance(clazz: KClass<*>): Any? =
        clazz.constructors.firstOrNull { it.parameters.isEmpty() }?.call()

    fun get(clazz: KClass<*>): Any? = clazz.objectInstance ?: instancesCache.get(clazz)

    operator fun get(clazz: Class<*>): Any? = get(clazz.kotlin)

    fun getAll(classes: Iterable<Class<*>>) = classes.asSequence().mapNotNull { get(it) }

    @JvmName("getAllKClass")
    fun getAll(classes: Iterable<KClass<*>>) = classes.asSequence().mapNotNull { get(it) }
}