package de.phyrone.zwie.server.module

interface EnableTaskRunner {
    suspend fun onEnable()

    companion object Handler : ModuleTaskHandler {
        override val name: String = "enable"

        override val order: ModuleOrder = ModuleOrder.ASC

        override suspend fun runTask(module: Any, metadata: ModuleMetadata) {
            if (module is EnableTaskRunner) {
                module.onEnable()
            }
        }

    }
}