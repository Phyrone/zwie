pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(
    "client",
    "client:backend",
    "server",
    "shared:protocol",
    "shared:common",
    "shared:const"
)