package de.phyrone.zwie.server.terminal

import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.DisableTaskRunner
import de.phyrone.zwie.server.module.EnableTaskRunner
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.runBlocking
import org.jline.reader.Completer
import org.jline.reader.EndOfFileException
import org.jline.reader.Highlighter
import org.jline.reader.History
import org.jline.reader.LineReader
import org.jline.reader.LineReaderBuilder
import org.jline.reader.UserInterruptException
import org.jline.reader.impl.history.DefaultHistory
import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module
import kotlin.system.exitProcess

@Module("core::terminal")
@DependsOn("core::commands")
class TerminalModule : EnableTaskRunner, DisableTaskRunner, KoinComponent {
    private val koinApplication by inject<KoinApplication>()
    private val promtFixer by inject<PromtFixer>()


    private var terminalReaderThread: Thread? = null

    override suspend fun onEnable() {
        koinApplication.modules(koinModule)
        promtFixer.startPromtFix()
        terminalReaderThread?.interrupt()
        terminalReaderThread = TerminalReaderThread().also { it.start() }

    }

    override suspend fun onDisable() {
        terminalReaderThread?.interrupt()
        terminalReaderThread = null
        promtFixer.stopPromtFix()
        koinApplication.unloadModules(koinModule)
    }

    class TerminalReaderThread : Thread("TerminalReader"), KoinComponent {

        private val lineReader by inject<LineReader>()
        private val commandHandler by inject<TerminalCommandHandler>()


        override fun run() = runBlocking {
            try {
                while (true) {
                    try {
                        val line = lineReader.readLine("> ")
                        try {
                            commandHandler.handleCommand(line)
                        } catch (e: Exception) {
                            logger.atSevere().withCause(e).log("error while handling command '%s'", line)
                        }
                    } catch (e: UserInterruptException) {
                        exitProcess(0)
                    } catch (e: EndOfFileException) {
                        exitProcess(0)
                    }
                }
            } catch (e: Throwable) {
                logger.atSevere().withCause(e).log("terminal crashed")
                exitProcess(1)
            }
        }

        companion object {
            private val logger = logger()


        }
    }

    companion object {
        private val logger = logger()
        private const val MAX_RESTARTS = 10

        private val koinModule = module {
            single { DefaultHistory() } bind History::class
            single {
                TerminalBuilder.builder()
                    .dumb(true)
                    .color(true)
                    .jna(true)
                    .system(true)
                    .encoding(Charsets.UTF_8)
                    .nativeSignals(true)
                    .build()
            } bind Terminal::class
            single {
                LineReaderBuilder.builder()
                    .terminal(get())
                    .completer(getOrNull())
                    .highlighter(getOrNull())
                    .expander(getOrNull())
                    .history(getOrNull())
                    .variable(LineReader.HISTORY_FILE, ".zwie_history")
                    .option(LineReader.Option.AUTO_FRESH_LINE, true)
                    .build()
            } bind LineReader::class
            single { DefaultRichTerminalFeatures(get()) } binds arrayOf(Highlighter::class, Completer::class)
            single { DefaultTerminalCommandHandler(get()) } bind TerminalCommandHandler::class
            single { PromtFixer(get()) }
        }

    }


}