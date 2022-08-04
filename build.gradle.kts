plugins{
    idea
    kotlin("jvm") version "1.7.10" apply false
}

repositories{
    mavenCentral()

}
tasks{
    task("setup"){
        dependsOn("client:install-shared-lib")
    }
}