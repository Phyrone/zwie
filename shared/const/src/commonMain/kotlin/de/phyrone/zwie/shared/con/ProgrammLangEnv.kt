package de.phyrone.zwie.shared.con

enum class ProgrammLangEnv {
    JVM,
    JS;

    companion object {
        fun current() = currentProgramLangEnv()
    }
}