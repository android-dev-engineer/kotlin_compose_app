package com.android.dev.engineer.kotlin.compose.data.shared_preferences

interface UserEncryptedSharedPrefs {
    fun getSessionId(): String
    fun saveSessionId(sessionId: String)
    fun clearAll()

    companion object {
        const val SHARED_PREFS_FILE_NAME = "user_encrypted_shared_prefs"
    }
}