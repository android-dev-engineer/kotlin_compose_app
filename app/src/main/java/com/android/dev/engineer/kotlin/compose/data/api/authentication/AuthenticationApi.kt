package com.android.dev.engineer.kotlin.compose.data.api.authentication

import com.android.dev.engineer.kotlin.compose.data.domain.network.Authenticated
import com.android.dev.engineer.kotlin.compose.data.domain.network.NewSession
import com.android.dev.engineer.kotlin.compose.data.domain.network.Session
import com.android.dev.engineer.kotlin.compose.data.domain.network.SignIn
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationApi {
    @GET("authentication/token/new")
    suspend fun getRequestToken(): Authenticated

    @POST("authentication/token/validate_with_login")
    suspend fun login(@Body signIn: SignIn): Authenticated

    @POST("authentication/session/new")
    suspend fun getNewSession(@Body session: Session): NewSession
}