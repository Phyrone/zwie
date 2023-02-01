import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.github.gradle.node.yarn.task.YarnTask

plugins {
    base
    id("com.github.node-gradle.node")
}
version = parent?.version ?: error("No parent project found")
group = parent?.group ?: error("No parent project found")

tasks {
    clean {
        delete("build")
        delete("node_modules")
        delete("yarn.lock")
        delete("package-lock.json")
        delete(".svelte-kit")
        delete("src-tauri/target")
    }
    yarn {
        //dependsOn(":client:backend:browserProductionLibraryDistribution")
        dependsOn("update-backend-release")
        this.inputs.files("package.json", "yarn.lock")
        this.outputs.dir("node_modules")
        this.inputs.dir(project(":client:backend").projectDir)
    }

    create<YarnTask>("add-task-backend-release") {

        dependsOn(":client:backend:snapshot-lib-version-release")
        val backendLibFolder = project(":client:backend").buildDir.resolve("productionLibrary")
        val path = "./${backendLibFolder.relativeTo(projectDir).path}"
        this.args.set(listOf("add", path))
        onlyIf {
            val packageJsonNode = ObjectMapper().readTree(projectDir.resolve("package.json")) as ObjectNode
            (packageJsonNode.get("dependencies") as ObjectNode).get("@client/backend")?.asText() != path
        }
    }

    create<YarnTask>("update-backend-release") {
        group = "utils"
        dependsOn("add-task-backend-release", ":client:backend:snapshot-lib-version-release")

        this.args.set(listOf("upgrade", "@client/backend"))
    }

    create<YarnTask>("add-task-backend-develop") {

        dependsOn(":client:backend:snapshot-lib-version-develop")
        val backendLibFolder = project(":client:backend").buildDir.resolve("developmentLibrary")
        val path = "./${backendLibFolder.relativeTo(projectDir).path}"
        this.args.set(listOf("add", path))
        onlyIf {
            val packageJsonNode = ObjectMapper().readTree(projectDir.resolve("package.json")) as ObjectNode
            (packageJsonNode.get("dependencies") as ObjectNode).get("@client/backend")?.asText() != path
        }
    }

    create<YarnTask>("update-backend-develop") {
        group = "utils"
        dependsOn("add-task-backend-develop", ":client:backend:snapshot-lib-version-develop")
        this.args.set(listOf("upgrade", "@client/backend"))
    }

    create<YarnTask>("build-web") {
        dependsOn("yarn")
        group = "build"
        this.args.set(listOf("run", "build"))
    }

    build {
        dependsOn("build-web")
    }
}
node {

}