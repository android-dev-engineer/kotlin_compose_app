package com.android.dev.engineer.kotlin.compose.data.domain.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Session(
    @Json(name = "request_token")
    val requestToken: String
)