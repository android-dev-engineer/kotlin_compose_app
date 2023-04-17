package com.android.dev.engineer.kotlin.compose.data.api.authentication

import com.android.dev.engineer.kotlin.compose.data.domain.network.Authenticated
import com.android.dev.engineer.kotlin.compose.data.domain.network.NewSession
import com.android.dev.engineer.kotlin.compose.data.domain.network.Session
import com.android.dev.engineer.kotlin.compose.data.domain.network.SignIn

interface AuthenticationRepository {
    suspend fun getRequestToken(): Authenticated
    suspend fun signIn(signIn: SignIn): Authenticated
    suspend fun getNewSession(session: Session): NewSession
}