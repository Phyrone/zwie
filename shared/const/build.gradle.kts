import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform
import java.time.LocalDateTime

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.squareup:kotlinpoet:1.12.0")
    }
}

plugins {
    idea
    kotlin("multiplatform")
    //id("com.google.devtools.ksp") version "1.7.20-1.0.7"
}

version = parent?.version ?: error("No parent project found")

repositories {
    mavenCentral()
}
val generatedFolder by lazy {
    File(buildDir, "generated/const").also { it.mkdirs() }
}

tasks {


    //task("")
    val genTask = create("write-generated-code") {
        outputs.dir(generatedFolder)
        createGeneatedKtFile().writeTo(File(generatedFolder, ""))
    }
    withType(){
        if(name.startsWith("compileKotlin")){
            dependsOn(genTask)
        }
    }
    withType<ProcessResources>() {
        dependsOn(genTask)
    }


}



fun createGeneatedKtFile() = FileSpec.builder("de.phyrone.zwie.shared.con", "generated")
    .addType(createBuildConstObject())
    .build()


fun createBuildConstObject(): TypeSpec {
    val buildTime = LocalDateTime.now()
    LocalDateTime.of(2022, 12, 26, 10, 57, 14, 437126)
    return TypeSpec.objectBuilder("BuildConst")
        .addProperty(
            PropertySpec.builder("VERSION", String::class)
                .addModifiers(KModifier.CONST)
                .addKdoc(
                    """
                    Version of the Project
                """.trimIndent()
                )
                .initializer("%S", version)
                .build()
        ).addProperty(
            PropertySpec.builder("KOTLIN_VERSION", String::class)
                .addModifiers(KModifier.CONST)
                .initializer("%S", kotlin.coreLibrariesVersion)
                .build()
        ).addProperty(
            PropertySpec.builder("BUILD_DATE_TIME", String::class)
                .addModifiers(KModifier.CONST)
                .initializer("%S", buildTime.toString())
                .build()
        ).addProperty(
            PropertySpec.builder("BUILD_JAVA_VERSION", String::class)
                .addModifiers(KModifier.CONST)
                .initializer("%S", JavaVersion.current().majorVersion)
                .build()
        ).addProperty(
            PropertySpec.builder("BUILD_GRADLE_VERSION", String::class)
                .addModifiers(KModifier.CONST)
                .initializer("%S", GradleVersion.current().version)
                .build()
        ).addProperty(
            PropertySpec.builder("BUILD_OS", String::class)
                .addModifiers(KModifier.CONST)
                .initializer("%S", DefaultNativePlatform.getCurrentOperatingSystem().name)
                .build()
        ).addProperty(
            PropertySpec.builder("BUILD_ARCH", String::class)
                .addModifiers(KModifier.CONST)
                .initializer("%S", DefaultNativePlatform.getCurrentArchitecture().name)
                .build()
        )
        .build()
}

kotlin {
    jvm()
    js(IR) {
        browser()
        binaries.library()
    }

    sourceSets {
        val commonMain by getting {}

        val generatedMain = create("generated") {
            dependsOn(commonMain)
            kotlin.srcDir(generatedFolder)
        }
        val jsMain by getting {
            dependsOn(generatedMain)
        }
        val jvmMain by getting {
            dependsOn(generatedMain)
        }


    }
}