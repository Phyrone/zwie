package de.phyrone.zwie.server.misc

import de.phyrone.zwie.server.module.DisableTaskRunner
import de.phyrone.zwie.server.module.EnableTaskRunner
import de.phyrone.zwie.server.module.Module
import org.koin.core.component.KoinComponent

@Module(
    name = "core::scheduler"
)
class SchedulerModule : EnableTaskRunner, DisableTaskRunner, KoinComponent {

    override suspend fun onEnable() {

    }

    override suspend fun onDisable() {

    }

}