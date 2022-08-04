import com.github.gradle.node.yarn.task.YarnTask

plugins {
    base
    id("com.github.node-gradle.node") version "3.4.0"
}

tasks {

    create<YarnTask>("run"){
        this.args.set(listOf("run","tauri","dev"))
    }

    create<YarnTask>("build-client"){
        this.args.set(listOf("run","tauri","build"))
    }
    clean{
        delete("${projectDir.absolutePath}/node_modules/")
        delete("${projectDir.absolutePath}/src-tauri/target/")
    }


}