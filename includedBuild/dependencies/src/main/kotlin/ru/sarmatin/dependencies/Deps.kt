package ru.sarmatin.dependencies

object Deps {


    object Versions {

        const val kotlin = "1.4.30"

        const val coroutines = "1.4.0"

        const val core_ktx = "1.2.0"

        const val lifecycle = "2.2.0"
        const val lifecycle_vm_saved_state = "1.0.0-rc03"

        const val firebase_crashlytics = "17.3.0"
        const val firebase_analytics = "18.0.0"

        const val appcompat = "1.1.0"
        const val material = "1.1.0"
        const val constraint_layout = "2.0.4"
        const val recyclerview = "1.1.0"
        const val preference = "1.1.1"

        const val navigation = "2.3.2"
        const val room = "2.2.5"

        const val desugaring = "1.0.9"

        const val junit = "4.+"
        const val junit_android_ext = "1.1.1"
        const val espresso = "3.2.0"

        const val hilt = "2.33-beta"

        const val timber = "4.7.1"
        const val gson = "2.8.6"

        const val retrofit = "2.7.1"
        const val retrofit_gson = "2.7.1"
        const val retrofit_logging_interceptor = "4.3.1"

        const val glide = "4.11.0"
        const val glide_transformations = "4.3.0"

        const val serialization = "1.0.1"

    }

    val plugins = Plugins


    val common = Common

    val kotlin = Kotlin
    val kotlinx = Kotlinx
    val android = Android

    val retrofit = Retrofit
    val glide = Glide

    val firebase = Firebase

    val di = DI

    val test = Test

    object Plugins {

        val classpath = ClasspathPlugins
        val project = ProjectPlugins

        object ClasspathPlugins {

            const val kotlin_plugin =
                "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
            const val android_plugin = "com.android.tools.build:gradle:4.1.2"
            const val safe_args_plugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

            const val hilt_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
        }

        object ProjectPlugins {

            const val android_app = "com.android.application"
            const val android_library = "com.android.library"

            const val kotlin_android = "kotlin-android"

            @Deprecated("Migrate to viewbindning")
            const val kotlin_android_ext = "kotlin-android-extensions"
            const val kotlin_kapt = "kotlin-kapt"

            const val navigation_safe_args = "androidx.navigation.safeargs.kotlin"

            const val hilt = "dagger.hilt.android.plugin"

        }

    }


    object Common {
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
    }

    object Kotlin {

        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

        val kotlin_serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"

        class StdLib(
            private val name: String = "org.jetbrains.kotlin:kotlin-stdlib:${{ Versions.kotlin }}"
        ) {
            //here you can provide different kotlin backends, for ex: js, native, common
            override fun toString(): String = name
        }

        object Test {
            const val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
            const val js = "org.jetbrains.kotlin:kotlin-test-js:${Versions.kotlin}"
            const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
            const val annotationsCommon =
                "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
        }

    }

    object Kotlinx {

        val coroutines = Coroutines
        val serialization_json =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"

        object Coroutines {
            val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

            //here also you can split by different platforms
            val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        }

    }

    object Android {

        val androidx = Androidx
        val tools = Tools

        const val material = "com.google.android.material:material:${Versions.material}"

        object Androidx {

            val lifecycle = Lifecycle
            val test = Test
            val navigation = Navigation
            val room = Room


            const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
            const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
            const val constraint_layout =
                "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
            const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
            const val preference = "androidx.preference:preference-ktx:${Versions.preference}"

            object Lifecycle {
                const val ext = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
                const val livedata_ktx =
                    "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
                const val vm = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
                const val vm_ktx =
                    "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
                const val kapt =
                    "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
                const val vm_saved_state =
                    "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
                const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
                const val service = "androidx.lifecycle:lifecycle-service:${Versions.lifecycle}"
            }

            object Navigation {
                const val fragment_ktx =
                    "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
                const val ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
            }

            object Room {
                const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
                const val room_compiler_kapt = "androidx.room:room-compiler:${Versions.room}"
            }

            object Test {
                const val junit_ext = "androidx.test.ext:junit:${Versions.junit_android_ext}"
                const val esspresso_core =
                    "androidx.test.espresso:espresso-core:${Versions.espresso}"
            }

        }

        object Tools {
            const val desugaring = "com.android.tools:desugar_jdk_libs:${Versions.desugaring}"
        }

    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
    }

    object DI {

        val hilt = Hilt

        object Hilt {
            const val core = "com.google.dagger:hilt-android:${Versions.hilt}"
            const val kapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        }

    }

    object Retrofit {

        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_gson}"
        const val okhttp3_logging_interceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.retrofit_logging_interceptor}"

    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val transformations =
            "jp.wasabeef:glide-transformations:${Versions.glide_transformations}"
        const val kapt_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object Firebase {
        const val crashlytics =
            "com.google.firebase:firebase-crashlytics-ktx:${Versions.firebase_crashlytics}"
        const val analytics =
            "com.google.firebase:firebase-analytics-ktx:${Versions.firebase_analytics}"
    }

}