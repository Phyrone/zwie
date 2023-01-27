package de.phyrone.zwie.shared.protocol.client

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual fun getPlatformClientEngine(): HttpClientEngineFactory<*> = OkHttp