package de.phyrone.zwie.server.terminal

import com.mojang.brigadier.exceptions.CommandSyntaxException
import de.phyrone.zwie.server.command.TerminalCommandContext
import de.phyrone.zwie.server.utils.ZwieCommandDispatcher

class DefaultTerminalCommandHandler(
    private val dispatcher: ZwieCommandDispatcher,
) : TerminalCommandHandler {


    override fun handleCommand(command: String) {
        val context = TerminalCommandContext(command)
        val trimmedCommand = command.trim()
        val parsed = dispatcher.parse(trimmedCommand, context)
        try {
            dispatcher.execute(parsed)
        } catch (e: CommandSyntaxException) {
            val helpNode = parsed.context.nodes.lastOrNull()?.node ?: dispatcher.root
            println(e.localizedMessage)
            println("Suggestions:")
            val prefix = trimmedCommand.substring(0, e.cursor).let { if(it.isEmpty()) "" else "${it.trimEnd()} "  }
            println(
                dispatcher.getSmartUsage(helpNode, context).map { (_, suggestion) -> suggestion }
                    .joinToString("\n") { " - $prefix$it" }
            )

        }
    }
}