package com.android.dev.engineer.kotlin.compose.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Hilt gradle plugin causes Jacoco coverage issue: https://github.com/google/dagger/issues/1982
@HiltAndroidApp(Application::class)
class KotlinComposeApp : Hilt_KotlinComposeApp()