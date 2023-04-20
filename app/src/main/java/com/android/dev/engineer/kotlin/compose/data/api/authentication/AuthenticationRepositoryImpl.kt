package com.android.dev.engineer.kotlin.compose.data.api.authentication

import com.android.dev.engineer.kotlin.compose.data.api.TheMovieApi
import com.android.dev.engineer.kotlin.compose.data.domain.network.NewSession
import com.android.dev.engineer.kotlin.compose.data.domain.network.Session
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val theMovieApi: TheMovieApi
) : AuthenticationRepository {
    override suspend fun getNewSession(session: Session): NewSession {
        return theMovieApi.getNewSession(session = session)
    }
}