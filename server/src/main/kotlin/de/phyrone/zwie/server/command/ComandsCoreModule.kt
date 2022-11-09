package de.phyrone.zwie.server.command

import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import de.phyrone.zwie.server.module.DisableTaskRunner
import de.phyrone.zwie.server.module.EnableTaskRunner
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.utils.ZwieCommandDispatcher
import de.phyrone.zwie.server.utils.lazyArg
import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jline.reader.LineReader
import org.jline.reader.impl.LineReaderImpl
import org.jline.terminal.impl.DumbTerminal
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module
import kotlin.system.exitProcess

@Module(
    name = "core::commands"
)
class ComandsCoreModule : EnableTaskRunner, DisableTaskRunner, KoinComponent {


    private val commandDispatcher by inject<ZwieCommandDispatcher>()
    private val lineReader by inject<LineReader>()
    private val koinApplication by inject<KoinApplication>()

    override suspend fun onEnable() {
        koinApplication.modules(koinModule)

        commandDispatcher.register(
            LiteralArgumentBuilder.literal<CommandContext>("help").executes { command ->
                val usage = commandDispatcher.getSmartUsage(command.rootNode, command.source)
                println("help:\n" + usage.map { (_, suggestion) -> " - $suggestion" }.joinToString("\n"))
                return@executes 0
            }
        )

        commandDispatcher.register(
            LiteralArgumentBuilder.literal<CommandContext?>("clear").executes { command ->
                (lineReader as? LineReaderImpl)?.clearScreen()
                lineReader.terminal.flush()
                return@executes 0
            }.requires { lineReader.terminal !is DumbTerminal && it is TerminalCommandContext })

        commandDispatcher.register(LiteralArgumentBuilder.literal<CommandContext?>("stop").executes { command ->
            exitProcess(0)
        })

        commandDispatcher.register(
            LiteralArgumentBuilder.literal<CommandContext>("exit").executes { command ->
                exitProcess(0)
            }.then(
                RequiredArgumentBuilder.argument<CommandContext, Int>(
                    "exit code",
                    IntegerArgumentType.integer()
                ).executes { command ->
                    exitProcess(command.getArgument("exit code", Int::class.java))
                }
            ))
        GlobalScope.launch(Dispatchers.Main) {
            if (logger.atFiner().isEnabled)
                commandDispatcher.root.children.forEach { commandNode ->
                    logger.atFiner().log("command found %s", lazyArg { commandNode.name })
                }
            if (logger.atWarning().isEnabled)
                commandDispatcher.findAmbiguities { parent, child, sibling, inputs ->
                    logger.atWarning().log(
                        "ambiguity found %s %s %s %s",
                        lazyArg { parent.name },
                        lazyArg { child.name },
                        lazyArg { sibling.name },
                        lazyArg { inputs })
                }
        }
    }

    override suspend fun onDisable() {
        koinApplication.unloadModules(koinModule)
    }

    companion object {
        private val logger = logger()

        private val koinModule = module {
            single(createdAtStart = true) { ZwieCommandDispatcher() }
        }
    }


}