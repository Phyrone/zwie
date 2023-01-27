package de.phyrone.zwie.shared.protocol.client

import io.ktor.client.engine.*

expect fun getPlatformClientEngine(): HttpClientEngineFactory<*>