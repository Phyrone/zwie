plugins {
    idea
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}
tasks {

}

kotlin {
    jvm()
    js(IR) {
        browser()
        binaries.library()
    }

    sourceSets {
        val jsMain by getting {

        }
        val jvmMain by getting {

        }
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:common"))
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.4.1")
                implementation("io.ktor:ktor-client-core:2.2.1")
            }
        }

    }
}