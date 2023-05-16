package com.android.dev.engineer.kotlin.compose.data.use_case.sign_in

import com.android.dev.engineer.kotlin.compose.data.domain.local.Authentication

interface LogInUseCase {
    suspend operator fun invoke(authentication: Authentication)
}