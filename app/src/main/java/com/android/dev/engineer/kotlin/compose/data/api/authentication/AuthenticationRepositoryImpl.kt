package com.android.dev.engineer.kotlin.compose.data.api.authentication

import com.android.dev.engineer.kotlin.compose.data.api.TheMovieApi
import com.android.dev.engineer.kotlin.compose.data.domain.network.*
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val theMovieApi: TheMovieApi
) : AuthenticationRepository {
    override suspend fun getRequestToken(): Authenticated {
        return theMovieApi.getRequestToken()
    }

    override suspend fun login(signIn: SignIn): Authenticated {
        return theMovieApi.login(signIn = signIn)
    }

    override suspend fun getNewSession(session: Session): NewSession {
        return theMovieApi.getNewSession(session = session)
    }

    override suspend fun logOut(logOut: LogOut) {
        theMovieApi.logOut(logOut)
    }
}