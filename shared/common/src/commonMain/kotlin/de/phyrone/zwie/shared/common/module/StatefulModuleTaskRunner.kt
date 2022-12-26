package de.phyrone.zwie.shared.common.module

interface StatefulModuleTaskRunner<Module : Any, State : Enum<*>> : ModuleTaskRunner<Module> {
    suspend fun goto(state: State, module: Module? = null, jump: Boolean = false)

}