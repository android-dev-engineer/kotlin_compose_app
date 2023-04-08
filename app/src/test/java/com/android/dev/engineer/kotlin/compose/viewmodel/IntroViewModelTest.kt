package com.android.dev.engineer.kotlin.compose.viewmodel

import com.android.dev.engineer.kotlin.compose.coroutines.MainTestRule
import com.android.dev.engineer.kotlin.compose.fake.MarkIntroCompleteUseFake
import com.android.dev.engineer.kotlin.compose.data.domain.MainNavGraph
import com.android.dev.engineer.kotlin.compose.feature.intro.IntroViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class IntroViewModelTest {
    @get:Rule
    val mainTestRule: MainTestRule = MainTestRule()
    private lateinit var markIntroCompleteUseCase: MarkIntroCompleteUseFake
    private lateinit var viewModel: IntroViewModel

    @Before
    fun setUp() {
        markIntroCompleteUseCase = MarkIntroCompleteUseFake()
        viewModel = IntroViewModel(markIntroCompleteUseCase = markIntroCompleteUseCase)
    }

    @Test
    fun `test mark intro as complete`() = runTest {
        markIntroCompleteUseCase.markIntroComplete = false
        viewModel.markIntroComplete()
        assertEquals(MainNavGraph.SignIn, viewModel.effect.first())
        assertEquals(true, markIntroCompleteUseCase.markIntroComplete)
    }
}