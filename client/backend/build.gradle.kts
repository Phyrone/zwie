plugins {
    kotlin("js")
}

repositories {
    mavenCentral()
}

version = parent?.version ?: error("No parent project found")
group = parent?.group ?: error("No parent project found")

dependencies {
    implementation(kotlin("stdlib-js"))

    implementation(project(":shared:const"))
    implementation(project(":shared:common"))
    implementation(project(":shared:protocol"))
    implementation(npm("svelte", "3.55.0"))
    implementation(npm("localforage", "1.10.0",false))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.6.4")
    implementation("io.insert-koin:koin-core:3.3.2")
}

kotlin {

    js(IR) {
        this.moduleName = "@client/backend"

        browser() {
            this.dceTask {
                this.enabled = true
            }
        }
        binaries.library()
    }
}
tasks {

}