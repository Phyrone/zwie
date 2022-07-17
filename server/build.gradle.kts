plugins{
    application
    kotlin("jvm")
    kotlin("kapt")
}
repositories{
    mavenCentral()
}
dependencies{
    implementation(project(":shared"))
    implementation("io.vertx:vertx-web:4.3.2")
    implementation("org.jetbrains.exposed:exposed-core:0.38.2")
    implementation("org.jetbrains.exposed:exposed-dao:0.38.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.38.2")
    implementation("org.jetbrains.exposed:exposed-java-time:0.38.2")

    implementation("org.atteo.classindex:classindex:3.11")
    kapt("org.atteo.classindex:classindex:3.11")
}

application{
    mainClass.set("de.phyrone.zwie.server.Main")
}