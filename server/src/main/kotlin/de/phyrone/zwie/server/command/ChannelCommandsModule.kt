package de.phyrone.zwie.server.command

import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.utils.ZwieCommandDispatcher
import org.koin.core.component.inject
import java.sql.Connection
import javax.sql.DataSource

@Module("core::commands::channel")
@DependsOn("core::commands")
class ChannelCommandsModule : CommonModule {

    private val commandDispatcher by inject<ZwieCommandDispatcher>()

    override suspend fun onEnable() {
        commandDispatcher.register(
            LiteralArgumentBuilder.literal<CommandContext>("channel")
                .then(
                    RequiredArgumentBuilder.argument<CommandContext, String>("channel", StringArgumentType.string())
                        .executes { context ->
                            val selectedChannel = context.getArgument("channel", String::class.java)
                            println("Selected Channel: $selectedChannel")
                            return@executes 0
                        }
                )
        )
    }
}