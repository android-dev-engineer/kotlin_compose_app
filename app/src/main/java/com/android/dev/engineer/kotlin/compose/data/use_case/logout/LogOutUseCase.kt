package com.android.dev.engineer.kotlin.compose.data.use_case.logout

interface LogOutUseCase {
    suspend operator fun invoke()
}