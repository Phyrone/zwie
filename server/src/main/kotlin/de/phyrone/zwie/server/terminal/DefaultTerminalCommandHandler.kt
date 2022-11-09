package de.phyrone.zwie.server.terminal

import com.mojang.brigadier.exceptions.CommandSyntaxException
import de.phyrone.zwie.server.command.TerminalCommandContext
import de.phyrone.zwie.server.utils.ZwieCommandDispatcher

class DefaultTerminalCommandHandler(
    val dispatcher: ZwieCommandDispatcher,
) : TerminalCommandHandler {


    override fun handleCommand(command: String) {
        try {
            dispatcher.execute(command.trim(), TerminalCommandContext(command))
        } catch (e: CommandSyntaxException) {
            println(e.localizedMessage)
        }
    }
}