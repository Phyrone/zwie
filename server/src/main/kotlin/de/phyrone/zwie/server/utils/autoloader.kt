package de.phyrone.zwie.server.utils

import com.github.benmanes.caffeine.cache.CacheLoader
import com.github.benmanes.caffeine.cache.Caffeine
import org.atteo.classindex.ClassIndex
import kotlin.reflect.KClass
import kotlin.reflect.full.hasAnnotation

/*
 * dependencies:
 *   - com.github.ben-manes.caffeine:caffeine:3.1.0
 *   - org.atteo.classindex:classindex:3.4
 */

@PublishedApi
internal val classCache = Caffeine.newBuilder()
    .weakValues()
    .build<Class<*>, Any> { clazz -> createInstance(clazz) }

private fun createInstance(clazz: Class<*>): Any =
    clazz.kotlin.objectInstance ?: clazz.getDeclaredConstructor().newInstance()


inline fun <reified A : Annotation, reified R> loadAnnotated() =
    ClassIndex.getAnnotated(A::class.java).toInstanceList<R>()


inline fun <reified T> load() = ClassIndex.getSubclasses(T::class.java).toInstanceList<T>()


@PublishedApi
internal inline fun <reified T> Iterable<Class<*>>.toInstanceList() = asSequence()
    .filterNot { clazz -> clazz.kotlin.hasAnnotation<SkipAutoLoad>() }
    .map { classCache[it] as T }
    .toList()

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class SkipAutoLoad