package de.phyrone.zwie.shared.common.module

interface ModuleTaskRunner<Module : Any> {
    suspend fun runTask(task: ModuleTask<Module>, module: Module? = null)

}