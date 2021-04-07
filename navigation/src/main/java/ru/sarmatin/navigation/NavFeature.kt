package ru.sarmatin.navigation

/**
 * Created by antonsarmatin
 * Date: 02/04/2021
 * Project: android-template-2021
 */
sealed class NavFeature {

    object SplashFeature : NavFeature()
    object AuthFeature : NavFeature()

}