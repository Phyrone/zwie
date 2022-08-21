package de.phyrone.zwie.server.terminal

import com.mojang.brigadier.AmbiguityConsumer
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.CommandSyntaxException
import de.phyrone.zwie.server.command.TerminalCommandContext
import de.phyrone.zwie.server.utils.ZwieCommandDispatcher
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.SearchStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import picocli.CommandLine

@Component
@ConditionalOnBean(TerminalRunner::class)
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