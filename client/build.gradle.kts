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
        dependsOn("update-backend")
        this.inputs.files("package.json", "yarn.lock")
        this.outputs.dir("node_modules")
        this.inputs.dir(project(":client:backend").projectDir)
    }

    create<YarnTask>("update-backend") {
        group = "utils"
        dependsOn(":client:backend:browserProductionLibraryDistribution")
        val backendLibFolder = project(":client:backend").buildDir.resolve("productionLibrary")
        this.args.set(listOf("add", backendLibFolder.absolutePath))
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