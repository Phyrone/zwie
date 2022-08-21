package de.phyrone.zwie.server.command

import com.mojang.brigadier.CommandDispatcher
import de.phyrone.zwie.server.utils.ZwieCommandDispatcher
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class CommandComponents {

    @Bean
    fun dispatcher() = ZwieCommandDispatcher()


}