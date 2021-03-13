package ru.sarmatin.gradle

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import ru.sarmatin.dependencies.Deps

/**
 * Configures build for module where it was applied
 * @see ProjectConfig
 * @see ModuleType
 * @see Deps
 */
class GradleConfigurationPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        if (project.isRootProject) {
            configureRootProject(project)
        } else {
            configureSubproject(project)
        }
    }

    /**
     * Checks if is plugin applied to root build.gradle.kts
     */
    private val Project.isRootProject: Boolean
        get() = parent == null

    private val Project.isAppModule: Boolean
        get() = name.startsWith("app", false)

    /**
     * Configures root project build.gradle.kts
     */
    private fun configureRootProject(project: Project) {

        project.allprojects {
            repositories {
                google()
                jcenter()
                maven { setUrl("https://jitpack.io") }
            }
        }

        project.tasks.register("clean", Delete::class.java) {
            delete(project.rootProject.buildDir)
        }
    }


    /**
     * Configures subprojects build.gradle.kts of root project
     * e.g.: app, features, data, domain, etc...
     */
    private fun configureSubproject(project: Project) {
        applyTopLevelPlugin(project)
        project.extensions.create(
            "moduleConfig",
            ModuleConfigurationExtension::class.java,
            project
        )
    }


    private fun applyTopLevelPlugin(project: Project) {
        //Применить андроид плагин нужно до всей остальной конфигурации, иначе не будет доступен dsl андроида в build.gradle
        if (project.isAppModule) {
            project.plugins.apply(Deps.plugins.project.android_app)
        } else {
            project.plugins.apply(Deps.plugins.project.android_library)
        }
    }

    fun config(project: Project, moduleType: ModuleType) {
        configurePlugins(project, moduleType)
        configureAndroidExtension(project, moduleType)

        project.tasks.withType(KotlinCompile::class.java).configureEach {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        configureDependencies(project, moduleType)
    }


    /**
     * Configures plugins for module defined by [ModuleType]
     * * @see ModuleType
     */
    private fun configurePlugins(project: Project, moduleType: ModuleType) {
        project.plugins.apply {
            //Стандартные плагины котлина
            apply(Deps.plugins.project.kotlin_android)
            apply(Deps.plugins.project.kotlin_android_ext)
            apply(Deps.plugins.project.kotlin_kapt)

            if (moduleType != ModuleType.CORE)
                apply(Deps.plugins.project.hilt)

            if (moduleType == ModuleType.APP
                || moduleType == ModuleType.FEATURE
                || moduleType == ModuleType.CORE_UI
            ) {
                apply(Deps.plugins.project.navigation_safe_args)
            }
        }
    }

    /**
     * Configures android{} block as it configured in default build.gradle.kts files
     */
    private fun configureAndroidExtension(project: Project, moduleType: ModuleType) {
        val extension = project.extensions.getByName("android")
        if (extension is BaseExtension) {
            extension.apply {
                compileSdkVersion(ProjectConfig.compileSdkVersion)
                buildToolsVersion(ProjectConfig.buildToolsVersion)
                defaultConfig {
                    targetSdkVersion(ProjectConfig.targetSdkVersion)
                    minSdkVersion(ProjectConfig.minSdkVersion)

                    if (moduleType == ModuleType.APP) {
                        //TODO git versioning in plugin, not build file
                        versionCode = 1
                        versionName = "1.0.0"
                    }

                }

                val proguardFile = "proguard-rules.pro"
                when (this) {
                    is LibraryExtension -> defaultConfig {
                        consumerProguardFiles(proguardFile)
                    }
                    is AppExtension -> buildTypes {
                        getByName("release") {
                            isMinifyEnabled = true
                            isShrinkResources = true
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                proguardFile
                            )
                        }
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
            }

        }
    }

    /**
     * Configures dependencies for module defined by [ModuleType]
     * @see ModuleType
     */
    private fun configureDependencies(project: Project, moduleType: ModuleType) {
        addCommonDependencies(project)

        when (moduleType) {
            ModuleType.APP, ModuleType.FEATURE -> {
                addAndroidDependencies(project)
                addUiCoreDependency(project)
                addAppFeatureDependencies(project)
                addNavigationDependency(project)
            }
            ModuleType.CORE_UI -> {
                addAndroidDependencies(project)
                addNavigationDependency(project)
            }
            ModuleType.NAVIGATION -> {
                addAndroidDependencies(project)
            }
            ModuleType.DOMAIN -> addDomainDependencies(project)
            ModuleType.DATA -> addDataDependencies(project)
        }

        if (moduleType != ModuleType.CORE && ProjectConfig.coreModuleName.isNotBlank()) {
            addCoreDependency(project)
        }
    }

    /**
     * Configures dependencies common to all project modules
     */
    private fun addCommonDependencies(project: Project) {
        //Base dependencies
        project.dependencies {
            add(IMPL, Deps.kotlin.stdlib)
            add(IMPL, Deps.kotlinx.coroutines.android)
            add(IMPL, Deps.android.androidx.core_ktx)

            add(IMPL, Deps.common.timber)
            add(IMPL, Deps.common.gson)

            add(IMPL_TEST, Deps.test.junit)
            add(IMPL_TEST_ANDROID, Deps.android.androidx.test.junit_ext)

        }
    }

    /**
     * Configures dependencies for app related modules (app, features, navigation, etc)
     * @see ModuleType.APP
     * @see ModuleType.FEATURE
     */
    private fun addAndroidDependencies(project: Project) {
        project.dependencies {

            add(IMPL, Deps.di.hilt.core)
            add(KAPT, Deps.di.hilt.kapt)

            add(IMPL, Deps.android.androidx.appcompat)
            add(IMPL, Deps.android.androidx.constraint_layout)
            add(IMPL, Deps.android.material)

            add(IMPL, Deps.android.androidx.preference)

            add(IMPL, Deps.android.androidx.lifecycle.livedata_ktx)
            add(IMPL, Deps.android.androidx.lifecycle.vm)
            add(IMPL, Deps.android.androidx.lifecycle.vm_ktx)
            add(IMPL, Deps.android.androidx.lifecycle.vm_saved_state)
            add(KAPT, Deps.android.androidx.lifecycle.kapt)

            add(IMPL, Deps.android.androidx.navigation.fragment_ktx)
            add(IMPL, Deps.android.androidx.navigation.ui_ktx)

            add(IMPL, Deps.glide.glide)
            add(IMPL, Deps.glide.transformations)
            add(KAPT, Deps.glide.kapt_compiler)

            add(IMPL_TEST_ANDROID, Deps.android.androidx.test.esspresso_core)
        }
    }

    /**
     * Configures dependencies for data modules
     * @see ModuleType.DATA
     */
    private fun addDataDependencies(project: Project) {
        project.dependencies {
            add(IMPL, Deps.di.hilt.core)
            add(KAPT, Deps.di.hilt.kapt)

            add(IMPL, Deps.android.androidx.appcompat)

            add(IMPL, Deps.android.androidx.preference)

            add(IMPL, Deps.retrofit.retrofit)
            add(IMPL, Deps.retrofit.converter_gson)
            add(IMPL, Deps.retrofit.okhttp3_logging_interceptor)

            add(IMPL, Deps.android.androidx.room.room_runtime)
            add(KAPT, Deps.android.androidx.room.room_compiler_kapt)
        }
    }

    /**
     * Configures dependencies for domain modules
     * @see ModuleType.DOMAIN
     */
    private fun addDomainDependencies(project: Project) {
        project.dependencies {
            add(IMPL, Deps.di.hilt.core)
            add(KAPT, Deps.di.hilt.kapt)
        }
    }

    //TODO Сделать добавление зависимостей между модулями на основе ModuleType,
    // без использования ProjectConfig полей,
    // например, после базовой конфигурации генерируя некий файл с Pair - (module nave, module type),
    // и далее проходя по нему связывать их

    /**
     * Configures dependencies between project modules
     */
    private fun addAppFeatureDependencies(project: Project) {
        if (project.isAppModule && ProjectConfig.featureDirectoryName.isNotBlank()) {
            getAllFeatureProject(project).forEach { featureProject ->
                println("add feature project: ${featureProject.name}")
                project.dependencies.add(
                    IMPL,
                    project.dependencies.project("path" to ":${ProjectConfig.featureDirectoryName}:${featureProject.name}")
                )
            }
        }
    }

    private fun addCoreDependency(project: Project) {
        checkNotNull(project.rootProject.subprojects.find { it.name == ProjectConfig.coreModuleName }) { "Core project not found, check module name in ProjectConfig file" }
        project.dependencies.add(
            IMPL,
            project.dependencies.project("path" to ":${ProjectConfig.coreModuleName}")
        )
    }

    private fun addUiCoreDependency(project: Project) {
        checkNotNull(project.rootProject.subprojects.find { it.name == ProjectConfig.uiCoreModuleName }) { "Ui core project not found, check module name in ProjectConfig file" }
        project.dependencies.add(
            IMPL,
            project.dependencies.project("path" to ":${ProjectConfig.uiCoreModuleName}")
        )
    }

    private fun addNavigationDependency(project: Project) {
        checkNotNull(project.rootProject.subprojects.find { it.name == ProjectConfig.navModuleName }) { "Navigation project not found, check module name in ProjectConfig file" }
        project.dependencies.add(
            IMPL,
            project.dependencies.project("path" to ":${ProjectConfig.navModuleName}")
        )
    }


    private fun getAllFeatureProject(project: Project): Set<Project> {
        return project.rootProject.subprojects
            .find { it.name == ProjectConfig.featureDirectoryName }
            ?.subprojects
            ?: emptySet()
    }

    companion object {

        //Deps config name
        const val API = "api"
        const val IMPL = "implementation"
        const val KAPT = "kapt"
        const val IMPL_TEST = "testImplementation"
        const val IMPL_TEST_ANDROID = "androidTestImplementation"

    }


}