package de.phyrone.zwie.server.terminal

import de.phyrone.zwie.server.command.TerminalCommandContext
import de.phyrone.zwie.server.utils.ZwieCommandDispatcher
import org.jline.reader.Candidate
import org.jline.reader.Completer
import org.jline.reader.Highlighter
import org.jline.reader.LineReader
import org.jline.reader.ParsedLine
import org.jline.utils.AttributedString
import org.jline.utils.AttributedStringBuilder
import org.jline.utils.AttributedStyle
import org.springframework.stereotype.Component
import java.util.regex.Pattern
import kotlin.math.min

@Component
class DefaultRichTerminalFeatures(val dispatcher: ZwieCommandDispatcher) : Highlighter, Completer {


    override fun highlight(reader: LineReader, buffer: String): AttributedString {
        if (buffer.isBlank())
            return AttributedString(buffer)
        val context = TerminalCommandContext(buffer)
        val parsed = dispatcher.parse(buffer.trim(), context)


        val suggestion = dispatcher.getCompletionSuggestions(dispatcher.parse(buffer.trimStart(), context))
            .get().list.firstOrNull()?.text ?: ""

        val offset = buffer.split(" ").lastOrNull()?.length ?: 0
        val completion = suggestion.substring(min(offset, suggestion.length))

        val color = if (parsed.reader.canRead()) AttributedStyle.RED else AttributedStyle.GREEN

        return AttributedStringBuilder.append(
            AttributedString(
                buffer,
                AttributedStyle().foreground(color)
            ),
            AttributedString(
                completion, AttributedStyle().foreground(color)
                    .underline()
            )

        )
    }

    override fun setErrorPattern(errorPattern: Pattern?) {}

    override fun setErrorIndex(errorIndex: Int) {}

    override fun complete(reader: LineReader, line: ParsedLine, candidates: MutableList<Candidate>) {
        val context = TerminalCommandContext(line.line())
        val read_line = line.line().trimStart()
        val trimmed = line.line().length - read_line.length
        val cursor = line.cursor() - trimmed
        val parsed = dispatcher.parse(read_line, context)
        candidates.addAll(dispatcher.getCompletionSuggestions(parsed, cursor).get().list.map { suggestion ->
            Candidate(suggestion.text)
        })
    }
}