package com.android.dev.engineer.kotlin.compose.data.api.authentication

import com.android.dev.engineer.kotlin.compose.data.domain.network.*

interface AuthenticationRepository {
    suspend fun getNewSession(session: Session): NewSession
    suspend fun getRequestToken(): Authenticated
    suspend fun login(signIn: SignIn): Authenticated
    suspend fun logOut(logOut: LogOut)
}