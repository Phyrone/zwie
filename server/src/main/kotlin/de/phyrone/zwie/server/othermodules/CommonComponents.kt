package de.phyrone.zwie.server.othermodules

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.cbor.databind.CBORMapper
import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.utils.findAndRegisterSubtypesAndModules
import org.koin.core.KoinApplication
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.koin.dsl.module

@Module("core::components")
class CommonComponents : CommonModule {

    private val koinApplication by inject<KoinApplication>()

    override suspend fun onEnable() {
        koinApplication.modules(koinModule)
    }

    override suspend fun onDisable() {
        koinApplication.unloadModules(koinModule)
    }

    companion object {
        private val koinModule = module {
            single(named("core::mapper::json")) { ObjectMapper().findAndRegisterSubtypesAndModules() }
            single(named("core::mapper::cbor")) { CBORMapper().findAndRegisterSubtypesAndModules() }
        }
    }

}