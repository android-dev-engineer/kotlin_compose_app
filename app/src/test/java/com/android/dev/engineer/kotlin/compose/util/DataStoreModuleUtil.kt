package com.android.dev.engineer.kotlin.compose.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.android.dev.engineer.kotlin.compose.data.di.DataStoreModule.provideAppPrefsDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

object DataStoreModuleUtil {
    private fun createCoroutineScopeTest(coroutineContext: CoroutineContext): CoroutineScope {
        return CoroutineScope(coroutineContext + Job())
    }

    fun provideAppPrefsDataStoreTest(
        appContext: Context,
        coroutineScope: CoroutineScope
    ): DataStore<Preferences> {
        return provideAppPrefsDataStore(
            appContext = appContext,
            coroutineScope = createCoroutineScopeTest(coroutineScope.coroutineContext)
        )
    }
}