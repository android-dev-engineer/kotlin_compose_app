package com.android.dev.engineer.kotlin.compose.test.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.test.core.app.ApplicationProvider
import com.android.dev.engineer.kotlin.compose.data.di.DataStoreModule.provideAppPrefsDataStore
import com.android.dev.engineer.kotlin.compose.data.datastore.app.AppDataStore
import com.android.dev.engineer.kotlin.compose.data.datastore.app.AppDataStoreImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class AppDataStoreTest {
    private lateinit var appPrefsDataStore: DataStore<Preferences>
    private lateinit var appDataStore: AppDataStore

    @Before
    fun setUp() {
        appPrefsDataStore = provideAppPrefsDataStore(
            appContext = ApplicationProvider.getApplicationContext(),
            coroutineScope = TestScope(UnconfinedTestDispatcher())
        )
        appDataStore = AppDataStoreImpl(dataStore = appPrefsDataStore)
    }

    @Test
    fun `test intro is pending`() = runTest {
        assertEquals(true, appDataStore.isIntroPending())
    }

    @Test
    fun `test when intro is complete`() = runTest {
        appDataStore.markIntroComplete()
        assertEquals(false, appDataStore.isIntroPending())
    }
}