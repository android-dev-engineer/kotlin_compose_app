package com.android.dev.engineer.kotlin.compose.data.domain.local

import com.android.dev.engineer.kotlin.compose.data.domain.network.SignIn

data class Authentication(
    val username: String,
    val password: String
)

fun Authentication.toSignIn(requestToken: String): SignIn = SignIn(
    username = username,
    password = password,
    requestToken = requestToken
)