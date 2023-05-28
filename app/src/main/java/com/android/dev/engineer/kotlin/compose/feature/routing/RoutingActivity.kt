package com.android.dev.engineer.kotlin.compose.feature.routing

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.android.dev.engineer.kotlin.compose.data.domain.local.MainNavGraph
import com.android.dev.engineer.kotlin.compose.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// Hilt gradle plugin causes Jacoco coverage issue: https://github.com/google/dagger/issues/1982
@AndroidEntryPoint(ComponentActivity::class)
class RoutingActivity : Hilt_RoutingActivity() {
    private val viewModel by viewModels<RoutingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        setUpObservers()
    }

    private fun setUpObservers() {
        lifecycleScope.launch {
            viewModel.effect.flowWithLifecycle(lifecycle).collectLatest(::updateEffect)
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