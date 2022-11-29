plugins {

    application
    kotlin("jvm")
    kotlin("kapt")
    //kotlin("plugin.spring")
    //kotlin("plugin.jpa")
    kotlin("plugin.allopen")
    id("com.github.johnrengelman.shadow")
    id("io.gitlab.arturbosch.detekt")
    //id("org.springframework.boot") version "2.7.5"
    //id("io.spring.dependency-management") version "1.1.0"
}

java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
}


dependencies {

    /*
    implementation("io.vertx:vertx-web:4.3.4")
    implementation("io.vertx:vertx-lang-kotlin:4.3.4")
    implementation("io.vertx:vertx-lang-kotlin-coroutines:4.3.4")
    implementation("io.vertx:vertx-sockjs-service-proxy:4.3.4")
    implementation("io.vertx:vertx-service-proxy:4.3.4")
     */


    implementation("io.ktor:ktor-server-core:2.1.3")
    implementation("io.ktor:ktor-server-netty:2.1.3")
    implementation("io.ktor:ktor-server-websockets:2.1.3")
    implementation("io.netty:netty-tcnative:2.0.54.Final")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.0")
    //implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-cbor:2.14.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-ion:2.14.0")
    //implementation("org.msgpack:jackson-dataformat-msgpack:0.9.3")

    implementation("com.esotericsoftware.kryo:kryo5:5.3.0")


    implementation("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.40.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")

    implementation("com.zaxxer:HikariCP:5.0.1")
    //implementation("org.greenrobot:eventbus-java:3.3.1")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.1")

    implementation("ch.qos.logback:logback-classic:1.4.4")

    implementation("info.picocli:picocli:4.7.0")
    implementation("info.picocli:picocli-shell-jline3:4.7.0")
    implementation("com.google.guava:guava:31.1-jre")
    //implementation("name.neuhalfen.projects.crypto.bouncycastle.openpgp:bouncy-gpg:2.3.0")


    implementation("io.insert-koin:koin-core:3.2.2")
    implementation("com.google.flogger:flogger:0.7.4")
    implementation("com.google.flogger:flogger-slf4j-backend:0.7.4")


    //implementation("com.google.flogger:flogger-log4j-backend:0.7.4")
    implementation("dev.onvoid.webrtc:webrtc-java:0.7.0")

    implementation("org.atteo.classindex:classindex:3.11")
    implementation("io.ktor:ktor-server-core-jvm:2.1.3")
    implementation("io.ktor:ktor-server-websockets-jvm:2.1.3")
    implementation("io.ktor:ktor-server-cors-jvm:2.1.3")
    implementation("io.ktor:ktor-server-call-logging-jvm:2.1.3")
    kapt("org.atteo.classindex:classindex:3.11")


    implementation("org.shredzone.acme4j:acme4j-client:2.14")
    implementation("org.shredzone.acme4j:acme4j-utils:2.14")
    implementation("org.shredzone.acme4j:acme4j-smime:2.14")

    implementation("org.greenrobot:eventbus-java:3.3.1")
    kapt("org.greenrobot:eventbus-annotation-processor:3.3.1")
    kapt("org.greenrobot:eventbus-annotation-processor:3.3.1")

    runtimeOnly("com.h2database:h2:2.1.214")

    implementation("com.coreoz:wisp:2.3.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")

    //for client
    implementation("io.rsocket.kotlin:rsocket-ktor-client:0.15.4")

    //for server
    implementation("io.rsocket.kotlin:rsocket-ktor-server:0.15.4")

    //implementation("org.apache.logging.log4j:log4j-core:2.18.0")
    //implementation("org.apache.logging.log4j:log4j-api:2.18.0")
    //implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.18.0")


    /*
    //implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    //implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    //implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    //runtimeOnly("io.r2dbc:r2dbc-h2")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

     */

    implementation("com.mojang:brigadier:1.0.18")
    implementation("org.jline:jline:3.21.0")

    implementation("org.fusesource.jansi:jansi:2.4.0")

    implementation("com.typesafe:config:1.4.2")
    implementation("io.github.config4k:config4k:0.5.0")

    //implementation("org.bouncycastle:bcpg-jdk15on:1.70")
    //implementation("org.bouncycastle:bcprov-jdk15on:1.70")
    //implementation("name.neuhalfen.projects.crypto.bouncycastle.openpgp:bouncy-gpg:2.3.0")

    implementation("com.github.oshi:oshi-core:6.3.2")

    implementation("org.pgpainless:pgpainless-core:1.4.0-rc1")


    implementation("net.folivo:trixnity-core:3.0.0-beta3")
    implementation("net.folivo:trixnity-client:3.0.0-beta3")
    implementation("net.folivo:trixnity-olm:3.0.0-beta3")
    //implementation("org.jobrunr:jobrunr:5.3.1")


}
tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    jar {
        archiveAppendix.set("no-dependencies")
    }

    shadowJar {
        mergeServiceFiles()
        mergeServiceFiles("/META-INF/annotations")

        archiveAppendix.set("")
    }


}

allOpen {
    //annotation("javax.persistence.Entity")
}

application {
    mainClass.set("de.phyrone.zwie.server.main.Main")
}
detekt {
    parallel = true
    ignoreFailures = true
    configurations {

    }
}
kapt{
    arguments {
        arg("eventBusIndex", "de.phyrone.zwie.server.gen.EventBusIndex")
    }
}