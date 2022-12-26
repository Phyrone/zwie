plugins {
    kotlin("multiplatform")
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
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

            }
        }

    }
}