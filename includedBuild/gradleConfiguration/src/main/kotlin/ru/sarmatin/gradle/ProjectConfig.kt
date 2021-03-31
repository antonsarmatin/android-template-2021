package ru.sarmatin.gradle

object ProjectConfig {

    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.1"

    const val targetSdkVersion = 30
    const val minSdkVersion = 21

    //TODO Сделать на основе moduleType
    //Для отключения какого-либо модуля - установить пустое значение
    //Название core модуля
    const val coreModuleName = "core"
    //Название core-ui модуля
    const val uiCoreModuleName = "common-ui"
    //Название navigation модуля
    const val navModuleName = "navigation"
    //Название domain модуля
    const val domainModuleName = "domain"
    //Название data модуля
    const val dataModuleName = "data"
    //Директория в которой находятся фичовые модули
    const val featureDirectoryName = "feature"


}