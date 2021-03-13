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
include(":feature:homescreen")
include(":feature:profile")
include(":core")
include(":common-ui")
