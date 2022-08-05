plugins{
    application
    kotlin("jvm")
    kotlin("kapt")
}
repositories{
    mavenCentral()
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

    implementation("io.ktor:ktor-server-core:2.0.3")
    implementation("io.ktor:ktor-server-netty:2.0.3")
    implementation("io.ktor:ktor-server-websockets:2.0.3")
    implementation("io.netty:netty-tcnative:2.0.54.Final")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")


    implementation("org.jetbrains.exposed:exposed-core:0.39.2")
    implementation("org.jetbrains.exposed:exposed-dao:0.39.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.39.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.39.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")

    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.greenrobot:eventbus-java:3.3.1")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.1")

    //TODO replace with log4j2 or logback
    implementation("org.slf4j:slf4j-simple:1.7.36")

    implementation("info.picocli:picocli:4.6.3")
    //implementation("name.neuhalfen.projects.crypto.bouncycastle.openpgp:bouncy-gpg:2.3.0")



    implementation("org.atteo.classindex:classindex:3.11")
    implementation("io.insert-koin:koin-core:3.2.0")
    implementation("com.google.flogger:flogger:0.7.4")
    implementation("com.google.flogger:flogger-slf4j-backend:0.7.4")
    implementation("dev.onvoid.webrtc:webrtc-java:0.6.0")
    kapt("org.atteo.classindex:classindex:3.11")
}

application{
    mainClass.set("de.phyrone.zwie.server.Main")
}