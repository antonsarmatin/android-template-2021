package ru.sarmatin.gradle

import org.gradle.api.Project
import javax.inject.Inject

/**
 * Adds module type extension for module configuration file
 */
open class ModuleConfigurationExtension @Inject constructor(private val project: Project) {

    private var isAlreadyProcessed = false
    var type: ModuleType = ModuleType.NOT_CONF
        set(value) {
            field = value
            onTypeSet(value)
        }


    private fun onTypeSet(moduleType: ModuleType) {
        if (isAlreadyProcessed) return

        project.plugins.findPlugin(GradleConfigurationPlugin::class.java)?.let {
            if(moduleType == ModuleType.NOT_CONF)
                throw IllegalArgumentException("Module not configured with proper ModuleType")
            it.config(project, moduleType)
            isAlreadyProcessed = true
        }
    }

}

enum class ModuleType {
    NOT_CONF,
    CORE,
    CORE_UI,
    APP,
    FEATURE,
    NAVIGATION,
    DOMAIN,
    DATA
}