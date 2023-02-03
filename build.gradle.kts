plugins {
    idea
    kotlin("jvm") version "1.8.10" apply false
    kotlin("js") version "1.8.10" apply false
    //kotlin("plugin.spring") version "1.7.20" apply false
    //kotlin("plugin.jpa") version "1.7.20" apply false
    kotlin("plugin.allopen") version "1.8.10" apply false
    kotlin("kapt") version "1.8.0" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("io.gitlab.arturbosch.detekt") version "1.22.0" apply false
    kotlin("multiplatform") version "1.8.0" apply false
    id("com.google.protobuf") version "0.9.2" apply false
    id("org.jetbrains.compose") version "1.3.0" apply false
    kotlin("plugin.serialization") version "1.8.0" apply false
    id("com.github.node-gradle.node") version "3.5.1" apply false
}

version = "0.0.1-DEV"

repositories {
    mavenCentral()


}