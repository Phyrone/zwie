package de.phyrone.zwie.server.user

import de.phyrone.zwie.server.utils.logger
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class UserManager(
    val publisher:ApplicationEventPublisher
) {
    companion object {
        private val logger = logger()
    }

}