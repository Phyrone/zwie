plugins {
    idea
    kotlin("multiplatform")
    //id("com.google.devtools.ksp") version "1.7.20-1.0.7"
}

version = parent?.version ?: error("No parent project found")
repositories {
    mavenCentral()
}

kotlin {
    jvm()
    js(IR) {
        browser()
        binaries.library()
        //nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
            }
        }

        val jsMain by getting {

            dependencies {
                implementation(npm("openpgp", "5.5.0", false))
                implementation(npm("crypto-js", "4.1.1", false))
                implementation(npm("@types/crypto-js", "4.1.1", false))
                implementation(npm("secure-random", "1.1.2", false))
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.6.4")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("org.pgpainless:pgpainless-core:1.4.2")
                implementation("org.bouncycastle:bcprov-jdk15on:1.68")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}