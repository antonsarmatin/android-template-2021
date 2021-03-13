import ru.sarmatin.gradle.ModuleType

plugins {
    id("gradle-configuration-plugin2")
}

moduleConfig{
    type = ModuleType.APP
}

android {
    defaultConfig {
        applicationId = "ru.sarmatin.template"
    }
}