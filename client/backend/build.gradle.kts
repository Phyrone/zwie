plugins {
    kotlin("js")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))

    implementation(project(":shared:const"))
    implementation(project(":shared:common"))
    implementation(npm("svelte", "3.53.1"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.6.4")
    implementation("io.insert-koin:koin-core:3.3.0")
}

kotlin {
    js(IR) {
        browser()
        binaries.library()
    }
}
tasks{

}