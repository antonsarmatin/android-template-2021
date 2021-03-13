plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

group = "ru.sarmatin.dependencies"
version = "SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

gradlePlugin {

    plugins.register("dependencies") {
        id = "dependencies"
        implementationClass = "ru.sarmatin.dependencies.DependenciesPlugin"
    }

}