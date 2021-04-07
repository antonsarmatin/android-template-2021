package ru.sarmatin.template

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import ru.sarmatin.template.commonui.platform.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.initialize(provideNavController())
    }

    override fun provideNavController(): NavController = findNavController(R.id.nav_host_fragment)

}