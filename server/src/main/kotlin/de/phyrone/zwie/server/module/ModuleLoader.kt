package de.phyrone.zwie.server.module

import de.phyrone.zwie.server.utils.lazyArg
import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.jvm.Throws
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.findAnnotations
import kotlin.system.measureNanoTime
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class ModuleLoader(
    modules: List<Any>,
) {
    companion object {
        private val logger = logger()
    }

    private val moduleMetadataPair =
        modules.mapNotNull { Pair(it, ModuleMetadataContainer.from(it) ?: return@mapNotNull null) }

    private data class ModuleMetadataContainer(
        override val name: String,
        val dependencies: List<DependsOn>,
    ) : ModuleMetadata {
        companion object {
            fun from(module: Any): ModuleMetadataContainer? {
                val annotation = module::class.findAnnotation<Module>() ?: return null
                return ModuleMetadataContainer(annotation.name, module::class.findAnnotations())
            }
        }
    }


    @OptIn(ExperimentalTime::class)
    suspend fun handleOrdered(handler: ModuleTaskHandler): List<Pair<String, Throwable>> {
        val (value, time) = measureTimedValue { ModuleTaskProgress(handler).run() }
        logger.atFine().log("Task ${handler.name} done ($time)")
        return value
    }

    enum class LifecycleState {
        NOT_STARTED, STARTED, WAITING, FINISHED, FAILED
    }

    private inner class ModuleTaskProgress(private val handler: ModuleTaskHandler) {

        val order = handler.order
        val subProgresses = moduleMetadataPair.map { (module, metadata) -> ModuleTaskSubProgress(module, metadata) }
        val nameToSubProgress = subProgresses.associateBy { it.name }


        suspend fun run() = coroutineScope {
            subProgresses.forEach { it.preRun() }
            subProgresses.map { launch { it.run() } }.forEach { it.join() }

            return@coroutineScope subProgresses.mapNotNull { Pair(it.name, it.exception ?: return@mapNotNull null) }
        }

        private inner class ModuleTaskSubProgress(val module: Any, private val metadata: ModuleMetadataContainer) {
            val name = metadata.name

            /*
            private var state = LifecycleState.NOT_STARTED
                set(value) {
                    logger.atFiner().log("[%s] %s -> %s", name, field, value)
                    field = value
                }*/

            val stateFlow = MutableStateFlow(LifecycleState.NOT_STARTED)

            var exception: Throwable? = null
                private set

            fun preRun() {
                logger.atFiner().log("[$name] looking for dependencies")
                for (dependency in metadata.dependencies) {
                    val pr = nameToSubProgress[dependency.name]
                    when {
                        pr != null -> {
                            if (dependency.loadBefore) {
                                pr.parents.add(this)
                                this.childs.add(pr)
                            } else {
                                pr.childs.add(this)
                                this.parents.add(pr)
                            }
                        }

                        dependency.optional -> continue
                        else -> error("Dependency ${dependency.name} not found for module $name")
                    }
                }

            }

            private val parents = mutableSetOf<ModuleTaskSubProgress>()
            private val childs = mutableSetOf<ModuleTaskSubProgress>()


            //private val notifyLock = Any()
            //private var cont = mutableSetOf<Continuation<Unit>>()

            @Throws(Exception::class, CancellationException::class)
            suspend fun awaitFinished() {
                when (val newState =
                    stateFlow.first { newState -> newState == LifecycleState.FINISHED || newState == LifecycleState.FAILED }) {
                    LifecycleState.FINISHED -> return
                    LifecycleState.FAILED -> throw exception!!
                    else -> error("Invalid State $newState")
                }
                /*
                suspendCancellableCoroutine { cancellableContinuation ->
                    synchronized(notifyLock) {
                        when (state) {
                            LifecycleState.FINISHED -> cancellableContinuation.resume(Unit)
                            LifecycleState.FAILED -> cancellableContinuation.resumeWithException(exception!!)
                            else -> {
                                cancellableContinuation.invokeOnCancellation {
                                    synchronized(notifyLock) {
                                        cont.remove(cancellableContinuation)
                                    }
                                }
                                cont.add(cancellableContinuation)
                            }
                        }
                    }
                }*/
            }

            /*
            private fun notifyFinished(e: Throwable? = null) {
                if (e == null) {
                    cont.forEach { it.resume(Unit) }
                } else {
                    cont.forEach { it.resumeWithException(e) }
                }
            }*/

            suspend fun run() {
                //state = LifecycleState.WAITING
                stateFlow.emit(LifecycleState.WAITING)
                try {
                    logger.atFiner().log("[%s] waiting for dependencies...", name)
                    val depedencyTime = measureNanoTime {
                        for (module in if (order == ModuleOrder.ASC) parents else childs) {
                            try {
                                module.awaitFinished()
                            } catch (e: Throwable) {
                                throw DependencyFailedException(module.name, e)
                            }
                        }
                    }
                    logger.atFine()
                        .log("[%s] waiting for dependencies finished (%s)", name, lazyArg { depedencyTime.nanoseconds })
                    stateFlow.emit(LifecycleState.STARTED)
                    //state = LifecycleState.STARTED

                    logger.atFine().log("[%s] running task: %s...", name, handler.name)
                    val taskRunTime = measureNanoTime {
                        handler.runTask(module, metadata)
                    }
                    logger.atFine()
                        .log("[%s] finished task: %s (%s)", name, handler.name, lazyArg { taskRunTime.nanoseconds })

                    stateFlow.emit(LifecycleState.FINISHED)
                    //synchronized(notifyLock) {
                    //    state = LifecycleState.FINISHED
                    //    notifyFinished()
                    //}
                } catch (e: Throwable) {
                    //synchronized(notifyLock) {
                    //    state = LifecycleState.FAILED
                    //    exception = e
                    //    notifyFinished(e)
                    //}
                    exception = e
                    stateFlow.emit(LifecycleState.FAILED)
                    logger.atWarning().withCause(e).log("[%s] failed", name)
                }
            }
        }
    }
}