plugins {
    idea
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}
repositories{
    mavenCentral()
}

kotlin {
    jvm()
    js(IR) {
        browser()
        binaries.library()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:2.2.2")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:2.2.1")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:common"))
                implementation(project(":shared:crypt"))
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.4.1")
                implementation("io.ktor:ktor-client-core:2.2.1")
                implementation("io.rsocket.kotlin:rsocket-core:0.15.4")
                implementation("io.rsocket.kotlin:rsocket-ktor-client:0.15.4")

            }
        }

    }
}