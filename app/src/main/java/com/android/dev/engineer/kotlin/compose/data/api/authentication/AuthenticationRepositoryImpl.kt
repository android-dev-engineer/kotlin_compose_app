package com.android.dev.engineer.kotlin.compose.data.api.authentication

import com.android.dev.engineer.kotlin.compose.data.api.TheMovieApi
import com.android.dev.engineer.kotlin.compose.data.domain.network.Authenticated
import com.android.dev.engineer.kotlin.compose.data.domain.network.NewSession
import com.android.dev.engineer.kotlin.compose.data.domain.network.Session
import com.android.dev.engineer.kotlin.compose.data.domain.network.SignIn
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val theMovieApi: TheMovieApi
) : AuthenticationRepository {
    override suspend fun getRequestToken(): Authenticated {
        return theMovieApi.getRequestToken()
    }

    override suspend fun signIn(signIn: SignIn): Authenticated {
        return theMovieApi.signIn(signIn = signIn)
    }

    override suspend fun getNewSession(session: Session): NewSession {
        return theMovieApi.newSession(session = session)
    }
}