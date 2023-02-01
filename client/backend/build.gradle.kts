import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import groovy.json.*
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinPackageJsonTask
import java.io.FileReader

plugins {
    kotlin("js")
}

repositories {
    mavenCentral()
}

version = parent?.version ?: error("No parent project found")
group = parent?.group ?: error("No parent project found")

dependencies {
    implementation(kotlin("stdlib-js"))

    implementation(project(":shared:const"))
    implementation(project(":shared:common"))
    implementation(project(":shared:protocol"))
    implementation(npm("svelte", "3.55.0"))
    implementation(npm("sweetalert2", "11.7.1"))
    implementation(npm("localforage", "1.10.0", false))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.6.4")
    implementation("io.insert-koin:koin-core:3.3.2")
}

kotlin {

    js(IR) {
        this.moduleName = "@client/backend"
        browser()
        binaries.library()
    }
}
tasks {

    create("snapshot-lib-version-release") {
        group = "utils"
        dependsOn("browserProductionLibraryDistribution")
        doLast {
            val packageJsonFile = buildDir.resolve("productionLibrary/package.json")
            val mapper = ObjectMapper()
            val jsonTree = mapper.readTree(packageJsonFile) as ObjectNode
            jsonTree.put("version", "0.0." + System.currentTimeMillis().toString())
            mapper.writerWithDefaultPrettyPrinter().writeValue(packageJsonFile, jsonTree)

            /*
            val packageJsonFile = buildDir.resolve("productionLibrary/package.json")
            val jsonSlurped = JsonSlurper().parse(packageJsonFile)
            println(jsonSlurped)
            val jsonBuilder = JsonBuilder(jsonSlurped)
            println(jsonBuilder.getProperty("version"))
            jsonBuilder.setProperty("version", System.currentTimeMillis().toString())
*/

        }
    }
    create("snapshot-lib-version-develop") {
        group = "utils"
        dependsOn("browserDevelopmentLibraryDistribution")
        doLast {
            val packageJsonFile = buildDir.resolve("developmentLibrary/package.json")
            val mapper = ObjectMapper()
            val jsonTree = mapper.readTree(packageJsonFile) as ObjectNode
            jsonTree.put("version", "0.0." + System.currentTimeMillis().toString())
            mapper.writerWithDefaultPrettyPrinter().writeValue(packageJsonFile, jsonTree)

            /*
            val packageJsonFile = buildDir.resolve("productionLibrary/package.json")
            val jsonSlurped = JsonSlurper().parse(packageJsonFile)
            println(jsonSlurped)
            val jsonBuilder = JsonBuilder(jsonSlurped)
            println(jsonBuilder.getProperty("version"))
            jsonBuilder.setProperty("version", System.currentTimeMillis().toString())
*/

        }
    }

}