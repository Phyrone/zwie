plugins {
    kotlin("multiplatform")
}
repositories{
    mavenCentral()
}
kotlin{
    jvm()
    js(IR){
        browser()
        binaries.library()
    }
}