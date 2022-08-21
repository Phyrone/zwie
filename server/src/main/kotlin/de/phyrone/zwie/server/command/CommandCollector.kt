package de.phyrone.zwie.server.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.tree.CommandNode
import de.phyrone.zwie.server.utils.ZwieCommandDispatcher
import de.phyrone.zwie.server.utils.lazyArg
import de.phyrone.zwie.server.utils.logger
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

//collects all commands in case of lazy loading
@Component
class CommandCollector(
    val commands: List<CommandNode<CommandContext>>,
    val dispatcher: ZwieCommandDispatcher,
) : ApplicationRunner {

    companion object {
        private val logger = logger()
    }

    override fun run(args: ApplicationArguments?) {
        logger.atInfo().log("found %d commands", lazyArg { commands.size })
        commands.forEach { commandNode ->
            logger.atFine().log("command found %s", lazyArg { commandNode.name })
        }
        dispatcher.findAmbiguities { parent, child, sibling, inputs ->
            logger.atWarning().log("ambiguity found %s %s %s %s", lazyArg { parent.name }, lazyArg { child.name }, lazyArg { sibling.name }, lazyArg { inputs })
        }


    }
}