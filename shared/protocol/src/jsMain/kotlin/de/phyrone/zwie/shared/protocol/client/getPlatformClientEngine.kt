package de.phyrone.zwie.shared.protocol.client

import io.ktor.client.engine.*
import io.ktor.client.engine.js.*

actual fun getPlatformClientEngine(): HttpClientEngineFactory<*> = Js