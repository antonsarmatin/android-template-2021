package ru.sarmatin.template

import android.app.Application
import timber.log.Timber

/**
 * Created by antonsarmatin
 * Date: 03/04/2021
 * Project: android-template-2021
 */
class App : Application() {

    private val isDebug = BuildConfig.DEBUG

    override fun onCreate() {
        super.onCreate()
        initInternals()
    }

    private fun initInternals(){
        initTimber()
    }

    private fun initTimber(){
        if (isDebug)
            Timber.plant(Timber.DebugTree())
    }

}