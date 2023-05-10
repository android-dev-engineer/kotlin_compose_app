package com.android.dev.engineer.kotlin.compose.data.shared_preferences

interface EncryptedSharedPrefs {
    fun getAccessToken(): String
    fun saveAccessToken(accessToken: String)
    fun clearAll()

    companion object {
        const val FILE_NAME = "encrypted_shared_preferences"
    }
}