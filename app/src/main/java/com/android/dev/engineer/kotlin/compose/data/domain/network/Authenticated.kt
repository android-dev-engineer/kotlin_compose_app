package com.android.dev.engineer.kotlin.compose.data.domain.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Authenticated(
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "expires_at")
    val expiresAt: String,
    @Json(name = "request_token")
    val requestToken: String
)