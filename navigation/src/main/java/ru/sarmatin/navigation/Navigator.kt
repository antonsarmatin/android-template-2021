package ru.sarmatin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import timber.log.Timber

/**
 * Created by antonsarmatin
 * Date: 03/04/2021
 * Project: android-template-2021
 */
abstract class Navigator {

    private var navController: NavController? = null

    fun initialize(navController: NavController) {
        this.navController = navController
    }

    private fun requireNavController(): NavController {
        return navController ?: throw RuntimeException("Navigator must be initialized")
    }

    protected fun navigate(to: NavDirections) {
        requireNavController().navigate(to)
    }

}