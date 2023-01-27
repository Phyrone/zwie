package de.phyrone.zwie.shared
enum class CTarget {
    JVM,
    JS;

    companion object {
        fun current() = currentCTarget()
    }
}