package de.phyrone.zwie.server.web.proto01

import de.phyrone.zwie.server.data.proto01.packets.payload.PayloadChannelLayout
import de.phyrone.zwie.server.data.proto01.packets.server.PacketServerUpdateChannelLayout
import de.phyrone.zwie.server.event.Proto01SessionSetupEvent
import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.Module
import io.ktor.websocket.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.core.component.inject

@Module("core::web::protocol01::channel")
@DependsOn("core::web::protocol01", loadBefore = true)
class Proto01ChannelModule : CommonModule {

    private val eventBus by inject<EventBus>()
    override suspend fun onEnable() {
        eventBus.register(this)
    }

    override suspend fun onDisable() {
        eventBus.unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun Proto01SessionSetupEvent.setupChannelHandler() {
        runTask {
            socket.send(
                PacketServerUpdateChannelLayout(
                    PayloadChannelLayout(0, "default (dummy)", "", 0, emptyList())
                )
            )
        }
    }
}