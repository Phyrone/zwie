package de.phyrone.zwie.server.event

import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module

class ComponentInitEvent (private val koinApplication: KoinApplication){
    fun addModule(createAtStart: Boolean = false, declation: ModuleDeclaration): Module {
        val module = module(createAtStart, declation)
        koinApplication.modules(module)
        return module
    }
}