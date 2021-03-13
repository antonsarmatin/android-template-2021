import ru.sarmatin.dependencies.Deps

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("dependencies")
}

repositories {
    google()
    jcenter()
    gradlePluginPortal()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    implementation(Deps.plugins.classpath.kotlin_plugin)
    implementation(Deps.plugins.classpath.android_plugin)
    implementation(Deps.plugins.classpath.safe_args_plugin)
    implementation(Deps.plugins.classpath.hilt_plugin)
}

kotlin.sourceSets.getByName("main").kotlin.srcDir("../dependencies/src/main/kotlin")

gradlePlugin {
    plugins.register("gradle-configuration-plugin") {
        id = "gradle-configuration-plugin2"
        implementationClass = "ru.sarmatin.gradle.GradleConfigurationPlugin"
    }
}