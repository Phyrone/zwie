package de.phyrone.zwie.server.othermodules

import com.esotericsoftware.kryo.kryo5.Kryo
import com.esotericsoftware.kryo.kryo5.util.Pool
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.ion.IonGenerator
import com.fasterxml.jackson.dataformat.ion.IonObjectMapper
import com.fasterxml.jackson.dataformat.ion.jsr310.IonJavaTimeModule
import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.utils.findAndRegisterSubtypesAndModules
import org.koin.core.KoinApplication
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

@Module("core::components")
class CommonComponents : CommonModule {

    private val koinApplication by inject<KoinApplication>()

    override suspend fun onEnable() {
        koinApplication.modules(koinModule)
    }

    override suspend fun onDisable() {}

    companion object {
        private val koinModule = module {
            single(named("core::mapper::json")) {
                ObjectMapper().findAndRegisterSubtypesAndModules()
            } bind ObjectMapper::class
            //single(named("core::mapper::cbor")) { CBORMapper().findAndRegisterSubtypesAndModules() } bind ObjectMapper::class
            //single(named("core::mapper::protobuf")) { ProtobufMapper().findAndRegisterSubtypesAndModules() } bind ObjectMapper::class
            single(named("core::mapper::ion")) {
                IonObjectMapper.builder().addModule(IonJavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .disable(IonGenerator.Feature.USE_NATIVE_TYPE_ID)
                    .build().findAndRegisterSubtypesAndModules()
            } bind ObjectMapper::class

            single(named("core::mapper::ion::binary")) {
                IonObjectMapper.builder().addModule(IonJavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .disable(IonGenerator.Feature.USE_NATIVE_TYPE_ID)
                    .build().findAndRegisterSubtypesAndModules().also { ionMapper ->
                        ionMapper.setCreateBinaryWriters(true)
                    }
            } bind ObjectMapper::class

            single(named("core::mapper::kryo")) {
                object : Pool<Kryo>(true, true) {
                    override fun create(): Kryo {
                        val kryo = Kryo()
                        kryo.isRegistrationRequired = false
                        kryo.warnUnregisteredClasses = false
                        kryo.references = true
                        kryo.setOptimizedGenerics(true)
                        kryo.setAutoReset(true)
                        return kryo
                    }
                }
            }

        }
    }
}