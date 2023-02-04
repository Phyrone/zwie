import de.phyrone.zwie.shared.common.events.Event
import de.phyrone.zwie.shared.common.events.EventBus
import de.phyrone.zwie.shared.common.events.subscribe
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import org.junit.Test
import kotlin.system.measureNanoTime
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.nanoseconds


class TestEventbus {


    @Test
    fun test1() {
        runBlocking(Dispatchers.Default) {
            val expectedCount = 1_000_000
            val eventCount = MutableStateFlow(0)
            val eventBus = EventBus()
            repeat(100) { eventBus.post(TestEvent2()) }
            eventBus.subscribe<TestEvent2> { event ->
                //println("event = $event")
                eventCount.update { it + 1 }
            }

            (0 until expectedCount).map {
                async { measureNanoTime { eventBus.post(TestEvent2()) } }
            }.awaitAll().average().nanoseconds.let { println("time = $it") }
            assertEquals(expectedCount, eventCount.value)
        }
    }

    @Test
    fun test2() {
        runBlocking {
            val flow = MutableSharedFlow<Event>(0, Int.MAX_VALUE, BufferOverflow.SUSPEND)

            repeat(100) {
                val time = measureNanoTime {
                    flow.emit(TestEvent2())
                    flow.emit(TestEvent2())
                }.nanoseconds
                println("time = $time")
            }
        }
    }
}

class TestEvent2 : Event