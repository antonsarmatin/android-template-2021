package ru.sarmatin.template.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import ru.sarmatin.navigation.NavFeature
import ru.sarmatin.navigation.ToFeatureNavigatable

/**
 * Created by antonsarmatin
 * Date: 01/04/2021
 * Project: android-template-2021
 */
class SplashFragment : Fragment(R.layout.fragment_splash) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            delay(3000)

            (requireActivity() as ToFeatureNavigatable).navigateToFeature(NavFeature.AuthFeature)
        }

    }

}