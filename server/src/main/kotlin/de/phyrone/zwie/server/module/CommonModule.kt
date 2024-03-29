package de.phyrone.zwie.server.module

import org.koin.core.component.KoinComponent

interface CommonModule : EnableTaskRunner, DisableTaskRunner, KoinComponent{

    override suspend fun onEnable() {}
    override suspend fun onDisable() {}
}