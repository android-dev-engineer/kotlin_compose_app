package com.android.dev.engineer.kotlin.compose.app

import android.app.Application
import com.android.dev.engineer.kotlin.compose.BuildConfig
import com.android.dev.engineer.kotlin.compose.data.timber.TimberDebugTree
import com.android.dev.engineer.kotlin.compose.util.ExcludeFromJacocoGeneratedReport
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
@ExcludeFromJacocoGeneratedReport
class HiltKotlinComposeApp : KotlinComposeApp()

open class KotlinComposeApp : Application() {
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