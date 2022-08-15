package de.phyrone.zwie.server.event

import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module

data class KoinStartupDoneEvent(
    val koinApplication: KoinApplication,
    val koin: Koin,
)