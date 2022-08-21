package de.phyrone.zwie.server.command

import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import de.phyrone.zwie.server.utils.ZwieCommandDispatcher
import org.jline.reader.LineReader
import org.jline.reader.impl.LineReaderImpl
import org.jline.terminal.impl.DumbTerminal
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import kotlin.system.exitProcess

@Component
class BasicCommands {


    @Bean
    fun exitCommand(commandDispatcher: ZwieCommandDispatcher) =
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

    @Bean
    fun stopCommand(commandDispatcher: ZwieCommandDispatcher) =
        commandDispatcher.register(LiteralArgumentBuilder.literal<CommandContext?>("stop").executes { command ->
            exitProcess(0)
        })

    @Bean
    fun helpCommand(commandDispatcher: ZwieCommandDispatcher) = commandDispatcher.register(
        LiteralArgumentBuilder.literal<CommandContext>("help").executes { command ->
            val usage = commandDispatcher.getSmartUsage(command.rootNode, command.source)
            println("help:\n" + usage.map { (_, suggestion) -> " - $suggestion" }.joinToString("\n"))
            return@executes 0
        }
    )

    @Bean
    fun clearConsoleCommand(lineReader: LineReader, commandDispatcher: ZwieCommandDispatcher) =
        commandDispatcher.register(
            LiteralArgumentBuilder.literal<CommandContext?>("clear").executes { command ->
                (lineReader as? LineReaderImpl)?.clearScreen()
                lineReader.terminal.flush()
                return@executes 0
            }.requires { lineReader.terminal !is DumbTerminal })
}