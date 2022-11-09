package de.phyrone.zwie.server.module

import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.module.ModuleOrder
import kotlin.jvm.Throws

interface ModuleTaskHandler {

    val name: String

    val order: ModuleOrder

    @Throws(Exception::class)
    suspend fun runTask(module: Any, metadata: Module)
}