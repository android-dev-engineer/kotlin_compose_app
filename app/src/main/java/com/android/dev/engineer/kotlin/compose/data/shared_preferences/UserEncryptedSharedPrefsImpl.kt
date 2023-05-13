package com.android.dev.engineer.kotlin.compose.data.shared_preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.android.dev.engineer.kotlin.compose.data.di.EncryptedSharedPrefsKey

// Use device file explorer to find it: data/data/com.android.dev.engineer.kotlin.compose/shared_prefs/encrypted_shared_preferences.xml
class UserEncryptedSharedPrefsImpl(
    @EncryptedSharedPrefsKey val sharedPreferences: SharedPreferences
) : UserEncryptedSharedPrefs {
    companion object {
        private const val ACCESS_TOKEN_KEY = "access_token_key"
    }

    @Synchronized
    override fun saveSessionId(sessionId: String) {
        sharedPreferences.edit {
            putString(ACCESS_TOKEN_KEY, sessionId)
        }
    }

    @Synchronized
    override fun getSessionId(): String {
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, "").orEmpty()
    }

    @Synchronized
    override fun clearAll() {
        sharedPreferences.edit {
            clear()
        }
    }
}