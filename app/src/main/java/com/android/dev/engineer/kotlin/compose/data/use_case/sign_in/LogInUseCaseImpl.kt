package com.android.dev.engineer.kotlin.compose.data.use_case.sign_in

import com.android.dev.engineer.kotlin.compose.data.api.authentication.AuthenticationRepository
import com.android.dev.engineer.kotlin.compose.data.di.IoDispatcher
import com.android.dev.engineer.kotlin.compose.data.domain.local.Authentication
import com.android.dev.engineer.kotlin.compose.data.domain.local.toSignIn
import com.android.dev.engineer.kotlin.compose.data.domain.network.Session
import com.android.dev.engineer.kotlin.compose.data.shared_preferences.UserEncryptedSharedPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogInUseCaseImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val authenticationRepository: AuthenticationRepository,
    private val userEncryptedSharedPrefs: UserEncryptedSharedPrefs
) : LogInUseCase {
    override suspend operator fun invoke(authentication: Authentication) = withContext(ioDispatcher) {
        val requestToken = authenticationRepository.getRequestToken().requestToken
        val authenticated = authenticationRepository.login(signIn = authentication.toSignIn(requestToken = requestToken))
        val newSession = authenticationRepository.getNewSession(session = Session(requestToken = authenticated.requestToken))
        userEncryptedSharedPrefs.saveSessionId(sessionId = newSession.sessionId)
    }
}