plugins{

    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("plugin.allopen")

    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
}

java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories{
    mavenCentral()
    maven("https://libraries.minecraft.net")
}


dependencies{

    /*
    implementation(project(":shared"))
    implementation("io.vertx:vertx-web:4.3.2")
    implementation("io.vertx:vertx-lang-kotlin:4.3.2")
    implementation("io.vertx:vertx-lang-kotlin-coroutines:4.3.2")
    implementation("io.vertx:vertx-sockjs-service-proxy:4.3.2")
    implementation("io.vertx:vertx-service-proxy:4.3.2")
    */

    implementation("io.ktor:ktor-server-core:2.1.1")
    implementation("io.ktor:ktor-server-netty:2.1.0")
    implementation("io.ktor:ktor-server-websockets:2.1.1")
    implementation("io.netty:netty-tcnative:2.0.54.Final")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")



    /*
    implementation("org.jetbrains.exposed:exposed-core:0.39.2")
    implementation("org.jetbrains.exposed:exposed-dao:0.39.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.39.2")
    implementation("org.jetbrains.exposed:exposed-java-time:0.39.2")
     */

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")

    //implementation("com.zaxxer:HikariCP:5.0.1")
    //implementation("org.greenrobot:eventbus-java:3.3.1")
    //implementation("com.github.ben-manes.caffeine:caffeine:3.1.1")

    //TODO replace with log4j2 or logback
    //implementation("org.slf4j:slf4j-simple:1.7.36")

    implementation("info.picocli:picocli:4.6.3")
    implementation("info.picocli:picocli-shell-jline3:4.6.3")
    implementation("com.google.guava:guava:31.1-jre")
    //implementation("name.neuhalfen.projects.crypto.bouncycastle.openpgp:bouncy-gpg:2.3.0")



    //implementation("org.atteo.classindex:classindex:3.11")
    //implementation("io.insert-koin:koin-core:3.2.0")
    implementation("com.google.flogger:flogger:0.7.4")
    implementation("com.google.flogger:flogger-slf4j-backend:0.7.4")


    //implementation("com.google.flogger:flogger-log4j-backend:0.7.4")
    implementation("dev.onvoid.webrtc:webrtc-java:0.6.0")
    kapt("org.atteo.classindex:classindex:3.11")


    implementation("org.shredzone.acme4j:acme4j-client:2.14")
    implementation("org.shredzone.acme4j:acme4j-utils:2.14")
    implementation("org.shredzone.acme4j:acme4j-smime:2.14")


    //implementation("org.apache.logging.log4j:log4j-core:2.18.0")
    //implementation("org.apache.logging.log4j:log4j-api:2.18.0")
    //implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.18.0")


    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
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

    implementation("com.mojang:brigadier:1.0.500")
    implementation("org.jline:jline:3.21.0")

}
allOpen{
    annotation("javax.persistence.Entity")
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

//application{ mainClass.set("de.phyrone.zwie.server.Main") }