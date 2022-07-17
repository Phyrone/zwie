plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.7.10"
}
repositories{
    mavenCentral()
}
kotlin{
    jvm()
    js(IR){
        browser()
        binaries.library()
    }
    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.3.3")
            }
        }
    }
}