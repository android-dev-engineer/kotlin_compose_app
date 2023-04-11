package com.android.dev.engineer.kotlin.compose.fake

import com.android.dev.engineer.kotlin.compose.data.use_case.mark_intro.MarkIntroCompleteUseCase

class MarkIntroCompleteUseCaseFake : MarkIntroCompleteUseCase {
    var isIntroPending = true
    var error: Exception? = null

    override suspend fun invoke() {
        error?.let { throw it }
        isIntroPending = false
    }
}