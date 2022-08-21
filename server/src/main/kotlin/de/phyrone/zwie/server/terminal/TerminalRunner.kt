package de.phyrone.zwie.server.terminal

import de.phyrone.zwie.server.utils.LineOutputStream
import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jline.reader.LineReader
import org.jline.reader.UserInterruptException
import org.jline.utils.AttributedString
import org.jline.utils.AttributedStyle
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.io.FileDescriptor
import java.io.FileOutputStream
import java.io.PrintStream
import kotlin.system.exitProcess

@Component
@ConditionalOnProperty(value = ["zwie.terminal.enabled"], matchIfMissing = true, havingValue = "true")
class TerminalRunner(
    private val lineReader: LineReader,
    private val commandHandler: TerminalCommandHandler,
    private val coroutineDispatcher: CoroutineDispatcher,

) : CommandLineRunner, DisposableBean {
    companion object {
        val logger = logger()

        const val MAX_RESTARTS = 10
    }


    private var terminal_reader_thread: Thread? = null

    override fun run(vararg args: String?) {
        startPromtFix()
        terminal_reader_thread?.interrupt()
        terminal_reader_thread = TerminalReaderThread().also { it.start() }
    }

    override fun destroy() {
        terminal_reader_thread?.interrupt()
        terminal_reader_thread = null
        stopPromtFix()
    }

    var crashes = 0

    inner class TerminalReaderThread : Thread("TerminalReader") {
        init {
            setUncaughtExceptionHandler { t, exception ->
                logger.atSevere().withCause(exception).log("terminal crashed %d times")
                if (crashes++ > MAX_RESTARTS) {
                    logger.atSevere().log("terminal crashed %d/$MAX_RESTARTS times, exiting", crashes)
                    exitProcess(1)
                } else {
                    logger.atSevere().log("terminal crashed %d/$MAX_RESTARTS times, restarting", crashes)
                    terminal_reader_thread = TerminalReaderThread().also { it.start() }
                }
            }
        }

        override fun run() = runBlocking {
            while (true) {
                try {
                    val line = lineReader.readLine("> ")
                    launch (coroutineDispatcher){
                        try {
                            commandHandler.handleCommand(line)
                        } catch (e: Exception) {
                            logger.atSevere().withCause(e).log("error while handling command '%s'", line)
                        }
                    }
                } catch (e: UserInterruptException) {
                    exitProcess(0)
                }
            }
        }
    }



    private var orginalSystemOutStream: PrintStream? = null
    private var orginalSystemErrStream: PrintStream? = null
    private fun startPromtFix() {


        val outStream = LineOutputStream { line -> lineReader.printAbove(AttributedString.fromAnsi(line)) }
        val erroutStream = LineOutputStream { line ->
            lineReader.printAbove(
                AttributedString(
                    line, AttributedStyle()
                        .foreground(AttributedStyle.RED)
                        .background(AttributedStyle.BLACK)
                )
            )
        }

        orginalSystemOutStream = System.out
        orginalSystemErrStream = System.err
        System.setOut(PrintStream(outStream, true))
        System.setErr(PrintStream(erroutStream, true))
    }

    private fun stopPromtFix() {
        System.setOut(orginalSystemOutStream ?: PrintStream(FileOutputStream(FileDescriptor.out), true))
        System.setErr(orginalSystemErrStream ?: PrintStream(FileOutputStream(FileDescriptor.err), true))

    }

}