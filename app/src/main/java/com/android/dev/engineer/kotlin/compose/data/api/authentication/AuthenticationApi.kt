package com.android.dev.engineer.kotlin.compose.data.api.authentication

import com.android.dev.engineer.kotlin.compose.data.domain.network.NewSession
import com.android.dev.engineer.kotlin.compose.data.domain.network.Session
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("authentication/session/new")
    suspend fun newSession(@Body session: Session): NewSession
}