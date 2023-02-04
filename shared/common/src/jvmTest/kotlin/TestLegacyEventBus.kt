import de.phyrone.zwie.shared.common.LegacyEventBus
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class TestLegacyEventBus {


    @Test
    fun testEventBus() {
        runBlocking {
            val eventBus = LegacyEventBus()
            var eventExecuteCount = 0
            var lateEventExecuteCount = 0
            eventBus.subscribe<TestEvent> {
                println("StickyEvent: ${it.value}")
                eventExecuteCount++
            }
            delay(10)
            eventBus.post(TestEvent(1))
            delay(100)
            eventBus.subscribe<TestEvent> {
                println("Late StickyEvent: ${it.value}")
                lateEventExecuteCount++
            }
            delay(100)
            assertEquals(1, eventExecuteCount, "eventExecuteCount")
            assertEquals(0, lateEventExecuteCount, "lateEventExecuteCount")
        }
    }

    @Test
    fun testEventBusSticky() {
        runBlocking {
            val eventBus = LegacyEventBus()
            var eventExecuteCount = 0
            var lateEventExecuteCount = 0
            eventBus.subscribe<TestEvent> {
                println("StickyEvent: ${it.value}")
                eventExecuteCount++
            }
            delay(10)
            eventBus.post(TestEvent(1),true)
            delay(100)
            eventBus.subscribe<TestEvent> {
                println("Late StickyEvent: ${it.value}")
                lateEventExecuteCount++
            }
            delay(100)
            assertEquals(1, eventExecuteCount, "eventExecuteCount")
            assertEquals(1, lateEventExecuteCount, "lateEventExecuteCount")
        }
    }
}

private class TestEvent(val value: Int)