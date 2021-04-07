package ru.sarmatin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import ru.sarmatin.template.navigation.NavGraphMainDirections

/**
 * Created by antonsarmatin
 * Date: 02/04/2021
 * Project: android-template-2021
 */
class FeatureNavigator : Navigator() {

    private var _navController: NavController? = null
        get() = field ?: throw RuntimeException("Navigator must be initialized")

    fun navigateToFeature(feature: NavFeature) {
        val direction: NavDirections = when (feature) {
            NavFeature.SplashFeature -> NavGraphMainDirections.actionGlobalNavFeatureSplash()
            NavFeature.AuthFeature -> NavGraphMainDirections.actionGlobalNavFeatureAuth()
        }
        navigate(direction)
    }

}