package com.android.dev.engineer.kotlin.compose.test.data.shared_preferences

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.dev.engineer.kotlin.compose.data.di.SharedPrefsModule.provideEncryptedSharedPrefs
import com.android.dev.engineer.kotlin.compose.data.shared_preferences.EncryptedSharedPrefs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class EncryptedSharedPrefsTest {
    private lateinit var encryptedSharedPrefs: EncryptedSharedPrefs

    @Before
    fun setUp() {
        encryptedSharedPrefs = provideEncryptedSharedPrefs(
            context = ApplicationProvider.getApplicationContext()
        )
    }

    @After
    fun tearDown() {
        encryptedSharedPrefs.clearAll()
    }

    @Test
    fun testAccessToken() = runTest {
        val accessToken = "my secret access token"
        encryptedSharedPrefs.saveAccessToken(accessToken = accessToken)
        assertEquals(accessToken, encryptedSharedPrefs.getAccessToken())
    }
}