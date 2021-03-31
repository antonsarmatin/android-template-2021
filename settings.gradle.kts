include(":domain")
include(":data")
pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
    }
}

rootProject.name = "android-template-2021"

includeBuild("includedBuild/dependencies")
includeBuild("includedBuild/gradleConfiguration")

include(":app")
include(":navigation")
include(":feature:splash")
include(":feature:auth")
include(":core")
include(":common-ui")
