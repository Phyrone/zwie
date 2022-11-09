plugins {
    idea
    kotlin("jvm") version "1.7.20" apply false
    //kotlin("plugin.spring") version "1.7.20" apply false
    //kotlin("plugin.jpa") version "1.7.20" apply false
    kotlin("plugin.allopen") version "1.7.20" apply false
    kotlin("kapt") version "1.7.20" apply false
}

repositories {
    mavenCentral()

}
tasks {
    task("setup") {
        dependsOn("client:install-shared-lib")
    }
}