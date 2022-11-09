package de.phyrone.zwie.server.terminal

import de.phyrone.zwie.server.utils.LineOutputStream
import org.jline.reader.LineReader
import org.jline.utils.AttributedString
import org.jline.utils.AttributedStyle
import java.io.FileDescriptor
import java.io.FileOutputStream
import java.io.PrintStream

class PromtFixer(
    private val lineReader: LineReader,
) {


    private var orginalSystemOutStream: PrintStream? = null
    private var orginalSystemErrStream: PrintStream? = null
     fun startPromtFix() {
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

     fun stopPromtFix() {
        System.setOut(orginalSystemOutStream ?: PrintStream(FileOutputStream(FileDescriptor.out), true))
        System.setErr(orginalSystemErrStream ?: PrintStream(FileOutputStream(FileDescriptor.err), true))
    }
}