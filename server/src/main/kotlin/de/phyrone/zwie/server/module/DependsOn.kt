package de.phyrone.zwie.server.module

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Repeatable
annotation class DependsOn(
    val name: String,
    val optional: Boolean = false,
    val loadBefore: Boolean = false,
)