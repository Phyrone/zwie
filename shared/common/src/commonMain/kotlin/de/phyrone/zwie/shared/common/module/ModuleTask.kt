package de.phyrone.zwie.shared.common.module

interface ModuleTask<T : Any> {
    val name: String
    val runBefore: Set<ModuleTask<T>>


    @Throws(Exception::class)
    suspend fun invoke(module: T)


}