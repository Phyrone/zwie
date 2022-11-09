package de.phyrone.zwie.server.module

interface DisableTaskRunner {
    suspend fun onDisable()

    companion object Handler : ModuleTaskHandler {
        override val name: String = "disable"
        override val order: ModuleOrder = ModuleOrder.DESC

        override suspend fun runTask(module: Any, metadata: Module) {
            if (module is DisableTaskRunner) {
                module.onDisable()
            }
        }

    }
}