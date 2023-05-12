package com.android.dev.engineer.kotlin.compose.data.api.authentication

import com.android.dev.engineer.kotlin.compose.data.domain.network.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface AuthenticationApi {
    @GET("authentication/token/new")
    suspend fun getRequestToken(): Authenticated

    @POST("authentication/token/validate_with_login")
    suspend fun login(@Body signIn: SignIn): Authenticated

    @POST("authentication/session/new")
    suspend fun getNewSession(@Body session: Session): NewSession

    // Delete with body: https://square.github.io/retrofit/2.x/retrofit/retrofit2/http/HTTP.html
    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    suspend fun logOut(@Body logOut: LogOut)
}