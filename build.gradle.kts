plugins {
    idea
    kotlin("jvm") version "1.7.22" apply false
    //kotlin("plugin.spring") version "1.7.20" apply false
    //kotlin("plugin.jpa") version "1.7.20" apply false
    kotlin("plugin.allopen") version "1.7.22" apply false
    kotlin("kapt") version "1.7.22" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("io.gitlab.arturbosch.detekt") version "1.22.0" apply false
}

repositories {
    mavenCentral()

}