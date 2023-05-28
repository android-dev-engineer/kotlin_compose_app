package com.android.dev.engineer.kotlin.compose.app

import android.app.Application
import com.android.dev.engineer.kotlin.compose.BuildConfig
import com.android.dev.engineer.kotlin.compose.data.timber.TimberDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

// Hilt gradle plugin causes Jacoco coverage issue: https://github.com/google/dagger/issues/1982
@HiltAndroidApp(Application::class)
class KotlinComposeApp : Hilt_KotlinComposeApp() {
    override fun onCreate() {
        super.onCreate()
        setUpTimber()
    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(TimberDebugTree())
        }
    }
}