package com.android.dev.engineer.kotlin.compose.fake

import com.android.dev.engineer.kotlin.compose.data.use_case.mark_intro.MarkIntroCompleteUseCase

class MarkIntroCompleteUseFake : MarkIntroCompleteUseCase {
    var markIntroComplete = false

    override suspend fun invoke() {
        markIntroComplete = true
    }
}