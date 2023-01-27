package de.phyrone.zwie.shared.con

enum class CompileTarget {
    JVM,
    JS;

    companion object {
        fun current() = currentProgramLangEnv()
    }
}