package com.android.dev.engineer.kotlin.compose.test.data.shared_preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.dev.engineer.kotlin.compose.data.di.SharedPrefsModule.provideEncryptedSharedPreferences
import com.android.dev.engineer.kotlin.compose.data.shared_preferences.UserEncryptedSharedPrefsImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class UserEncryptedSharedPrefsTest {
    companion object {
        private const val FAKE_SESSION_ID = "fake_session_id"
    }

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userEncryptedSharedPrefs: UserEncryptedSharedPrefsImpl

    @Before
    fun setUp() {
        sharedPreferences = provideEncryptedSharedPreferences(context = ApplicationProvider.getApplicationContext())
        userEncryptedSharedPrefs = UserEncryptedSharedPrefsImpl(sharedPreferences = sharedPreferences)
    }

    @After
    fun tearDown() {
        sharedPreferences.edit { clear() }
    }

    @Test
    fun testSaveSessionId() = runTest {
        userEncryptedSharedPrefs.saveSessionId(sessionId = FAKE_SESSION_ID)
        assertEquals(FAKE_SESSION_ID, userEncryptedSharedPrefs.getSessionId())
    }

    @Test
    fun testClearAll() = runTest {
        userEncryptedSharedPrefs.saveSessionId(sessionId = FAKE_SESSION_ID)
        userEncryptedSharedPrefs.clearAll()
        assertEquals("", userEncryptedSharedPrefs.getSessionId())
    }
}