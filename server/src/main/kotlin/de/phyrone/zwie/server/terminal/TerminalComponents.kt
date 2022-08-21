package de.phyrone.zwie.server.terminal

import org.jline.reader.Completer
import org.jline.reader.Expander
import org.jline.reader.Highlighter
import org.jline.reader.History
import org.jline.reader.LineReader
import org.jline.reader.LineReaderBuilder
import org.jline.reader.impl.history.DefaultHistory
import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


@Component
@ConditionalOnBean(TerminalRunner::class)
class TerminalComponents {


    @Bean(initMethod = "load", destroyMethod = "save", autowireCandidate = false)
    fun history(lineReader: LineReader) = DefaultHistory(lineReader)


    @Bean()
    fun terminal(): Terminal = TerminalBuilder.builder()
        .dumb(true)
        .color(true)
        .jna(true)
        .system(true)
        .encoding(Charsets.UTF_8)
        .nativeSignals(true)
        .build()

    @Bean
    @ConditionalOnMissingBean(LineReaderBuilder::class)
    fun lineReader(
        terminal: Terminal,
        @Autowired(required = false)
        highlighter: Highlighter?,
        @Autowired(required = false)
        completer: Completer?,
        @Autowired(required = false)
        expander: Expander?,
        @Autowired(required = false)
        history: History?,
    ): LineReader = LineReaderBuilder.builder()
        .terminal(terminal)
        .completer(completer)
        .highlighter(highlighter)
        .expander(expander)
        .history(history)
        .variable(LineReader.HISTORY_FILE, ".zwie_history")
        .option(LineReader.Option.AUTO_FRESH_LINE, true)
        .build()
}