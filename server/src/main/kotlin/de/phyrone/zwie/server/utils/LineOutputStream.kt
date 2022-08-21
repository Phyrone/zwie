package de.phyrone.zwie.server.utils

import java.io.ByteArrayOutputStream
import java.nio.charset.Charset
import java.util.function.Consumer

class LineOutputStream(private val consumer: Consumer<String>) : ByteArrayOutputStream() {

    private var buffer: String = ""
    override fun flush() {
        val lines = (buffer + toString(Charset.defaultCharset())).split(System.lineSeparator(), "\n")
        if (lines.size > 1) lines.subList(0, lines.size - 1).forEach { consumer.accept(it) }
        buffer = lines.last()
        reset()
    }
}