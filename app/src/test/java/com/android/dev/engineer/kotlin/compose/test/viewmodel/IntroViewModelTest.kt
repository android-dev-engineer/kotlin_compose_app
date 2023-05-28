package com.android.dev.engineer.kotlin.compose.test.viewmodel

import com.android.dev.engineer.kotlin.compose.coroutines.MainTestRule
import com.android.dev.engineer.kotlin.compose.data.domain.local.MainNavGraph
import com.android.dev.engineer.kotlin.compose.fake.MarkIntroCompleteUseCaseFake
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
    private lateinit var markIntroCompleteUseCase: MarkIntroCompleteUseCaseFake
    private lateinit var viewModel: IntroViewModel

    @Before
    fun setUp() {
        markIntroCompleteUseCase = MarkIntroCompleteUseCaseFake()
        viewModel = IntroViewModel(markIntroCompleteUseCase = markIntroCompleteUseCase)
    }

    @Test
    fun `test mark intro as complete and move to sign in`() = runTest {
        markIntroCompleteUseCase.isIntroPending = false
        viewModel.markIntroComplete()
        assertEquals(MainNavGraph.SignIn, viewModel.effect.first())
        assertEquals(false, markIntroCompleteUseCase.isIntroPending)
    }

    @Test
    fun `test mark intro failure and move to sign in`() = runTest {
        markIntroCompleteUseCase.error = Exception()
        viewModel.markIntroComplete()
        assertEquals(MainNavGraph.SignIn, viewModel.effect.first())
        assertEquals(true, markIntroCompleteUseCase.isIntroPending)
    }
}