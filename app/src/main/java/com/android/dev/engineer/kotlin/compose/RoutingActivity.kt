package com.android.dev.engineer.kotlin.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.android.dev.engineer.kotlin.compose.data.domain.MainNavGraph
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class RoutingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        initDelay()
    }

    // TODO remove it after adding ViewModel
    private fun initDelay() {
        lifecycleScope.launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        val bundle = MainActivity.prepareBundle(MainNavGraph.Intro.route)
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtras(bundle)
        }
        startActivity(intent)
        finish()
    }
}