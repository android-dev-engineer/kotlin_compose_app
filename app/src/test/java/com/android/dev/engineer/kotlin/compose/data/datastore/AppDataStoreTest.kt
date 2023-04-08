package com.android.dev.engineer.kotlin.compose.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.test.core.app.ApplicationProvider
import com.android.dev.engineer.kotlin.compose.coroutines.MainTestRule
import com.android.dev.engineer.kotlin.compose.data.local.AppDataStore
import com.android.dev.engineer.kotlin.compose.data.local.AppDataStoreImpl
import com.android.dev.engineer.kotlin.compose.util.DataStoreModuleUtil.provideAppPrefsDataStoreTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class AppDataStoreTest {
    @get:Rule
    val mainTestRule: MainTestRule = MainTestRule()

    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var appDataStore: AppDataStore

    @Before
    fun setUp() {
        dataStore = provideAppPrefsDataStoreTest(
            appContext = ApplicationProvider.getApplicationContext(),
            coroutineScope = mainTestRule.testScope
        )
        appDataStore = AppDataStoreImpl(dataStore = dataStore)
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