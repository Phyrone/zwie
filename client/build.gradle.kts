import com.github.gradle.node.yarn.task.YarnTask

plugins {
    id("com.github.node-gradle.node") version "3.4.0"
}

tasks {

    create<YarnTask>("install-shared-lib") {
        dependsOn(":shared:jsBrowserProductionLibraryDistribution")
        inputs.dir("${project(":shared").buildDir.absolutePath}/productionLibrary")
        outputs.dir("${projectDir.absolutePath}/node_modules/zwie-shared/")
        this.args.set(
            listOf(
                "add",
                "file:${project(":shared").buildDir.absolutePath}/productionLibrary/"
            )
        )
    }

    create<YarnTask>("build-client"){
        dependsOn("install-shared-lib")
        this.args.set(listOf("run","tauri","build"))
    }

}