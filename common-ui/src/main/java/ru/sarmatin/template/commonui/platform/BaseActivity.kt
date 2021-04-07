package ru.sarmatin.template.commonui.platform

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import ru.sarmatin.navigation.FeatureNavigator
import ru.sarmatin.navigation.NavFeature
import ru.sarmatin.navigation.ToFeatureNavigatable

/**
 * Created by antonsarmatin
 * Date: 02/04/2021
 * Project: android-template-2021
 */
abstract class BaseActivity(@LayoutRes layout: Int) : AppCompatActivity(layout), ToFeatureNavigatable {

    protected val navigator: FeatureNavigator = FeatureNavigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        navigator.initialize(provideNavController())
    }

    abstract fun provideNavController(): NavController

    override fun navigateToFeature(feature: NavFeature) {
        navigator.navigateToFeature(feature)
    }

}