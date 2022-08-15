package de.phyrone.zwie.server.event

import io.ktor.server.engine.*

data class WebSetupEvent(val builder:ApplicationEngineEnvironmentBuilder)