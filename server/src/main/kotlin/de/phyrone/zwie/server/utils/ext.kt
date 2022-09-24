package de.phyrone.zwie.server.utils

import io.vertx.core.AsyncResult

operator fun <T> AsyncResult<T>.component1(): T? = result()
operator fun <T> AsyncResult<T>.component2(): Throwable? = cause()