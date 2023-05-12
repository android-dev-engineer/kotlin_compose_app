package com.android.dev.engineer.kotlin.compose.data.use_case.logout

import com.android.dev.engineer.kotlin.compose.data.api.authentication.AuthenticationRepository
import com.android.dev.engineer.kotlin.compose.data.di.IoDispatcher
import com.android.dev.engineer.kotlin.compose.data.domain.network.LogOut
import com.android.dev.engineer.kotlin.compose.data.shared_preferences.UserEncryptedSharedPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogOutUseCaseImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val authenticationRepository: AuthenticationRepository,
    private val userEncryptedSharedPrefs: UserEncryptedSharedPrefs
) : LogOutUseCase {
    override suspend operator fun invoke() = withContext(ioDispatcher) {
        val sessionId = userEncryptedSharedPrefs.getSessionId()
        authenticationRepository.logOut(logOut = LogOut(sessionId = sessionId))
        userEncryptedSharedPrefs.clearAll()
    }
}