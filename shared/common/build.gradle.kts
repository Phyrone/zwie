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

            dependencies{
                implementation("com.github.ben-manes.caffeine:caffeine:3.1.2")
                implementation("com.sksamuel.aedile:aedile-core:1.2.0")
                implementation("org.jgrapht:jgrapht-core:1.5.1")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(kotlin("reflect"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(kotlin("test-junit"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
            }
        }
    }
}