package de.phyrone.zwie.server.module

import org.atteo.classindex.IndexAnnotated

@IndexAnnotated
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Module(
    val name: String,
    //val dependencies: Array<Dependency> = [],

)

