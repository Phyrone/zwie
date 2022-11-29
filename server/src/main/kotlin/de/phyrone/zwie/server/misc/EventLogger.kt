package de.phyrone.zwie.server.misc

import de.phyrone.zwie.server.event.MainThreadStartedEvent
import de.phyrone.zwie.server.utils.logger
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventLogger {

    @Subscribe(
        priority = Int.MAX_VALUE,
        threadMode = ThreadMode.BACKGROUND,
        sticky = false
    )
    fun Any.logEvent() {
        logger.atFine().log("event %s", this)
    }

    @Subscribe(
        priority = Int.MAX_VALUE,
        threadMode = ThreadMode.MAIN,
        sticky = true
    )
    fun MainThreadStartedEvent.logMainThreadStarted(){
        logger.atFine().log("Main Thread Started...")
    }
    companion object {
        private val logger = logger()
    }


}