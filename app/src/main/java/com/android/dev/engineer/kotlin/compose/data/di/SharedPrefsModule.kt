package com.android.dev.engineer.kotlin.compose.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.android.dev.engineer.kotlin.compose.data.shared_preferences.UserEncryptedSharedPrefs
import com.android.dev.engineer.kotlin.compose.data.shared_preferences.UserEncryptedSharedPrefs.Companion.FILE_NAME
import com.android.dev.engineer.kotlin.compose.data.shared_preferences.UserEncryptedSharedPrefsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefsModule {
    @EncryptedSharedPrefsKey
    @Singleton
    @Provides
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
        return EncryptedSharedPreferences.create(
            FILE_NAME,
            mainKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Singleton
    @Provides
    fun provideUserEncryptedSharedPrefs(userEncryptedSharedPrefsImpl: UserEncryptedSharedPrefsImpl): UserEncryptedSharedPrefs {
        return userEncryptedSharedPrefsImpl
    }
}