package com.android.dev.engineer.kotlin.compose.data.api.authentication

import com.android.dev.engineer.kotlin.compose.data.api.UnifiedApi
import com.android.dev.engineer.kotlin.compose.data.domain.network.Authenticated
import com.android.dev.engineer.kotlin.compose.data.domain.network.NewSession
import com.android.dev.engineer.kotlin.compose.data.domain.network.Session
import com.android.dev.engineer.kotlin.compose.data.domain.network.SignIn
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val unifiedApi: UnifiedApi
) : AuthenticationRepository {
    override suspend fun getRequestToken(): Authenticated {
        return unifiedApi.getRequestToken()
    }

    override suspend fun signIn(signIn: SignIn): Authenticated {
        return unifiedApi.signIn(signIn = signIn)
    }

    override suspend fun getNewSession(session: Session): NewSession {
        return unifiedApi.newSession(session = session)
    }
}