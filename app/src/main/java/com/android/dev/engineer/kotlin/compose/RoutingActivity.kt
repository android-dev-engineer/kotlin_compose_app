package com.android.dev.engineer.kotlin.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.android.dev.engineer.kotlin.compose.data.domain.MainNavGraph
import com.android.dev.engineer.kotlin.compose.feature.routing.RoutingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoutingActivity : ComponentActivity() {
    private val viewModel by viewModels<RoutingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        setUpObservers()
    }

    private fun setUpObservers() {
        lifecycleScope.launch {
            viewModel.effect.flowWithLifecycle(lifecycle).collect(::updateEffect)
        }
    }

    private fun updateEffect(mainNavGraph: MainNavGraph) {
        val bundle = MainActivity.prepareBundle(startDestination = mainNavGraph.route)
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtras(bundle)
        }
        startActivity(intent)
        finish()
    }
}