package de.phyrone.zwie.server.terminal

interface TerminalCommandHandler {

    fun handleCommand(command: String)
}