package com.android.dev.engineer.kotlin.compose.use_case

import com.android.dev.engineer.kotlin.compose.composable.coroutines.MainTestRule
import com.android.dev.engineer.kotlin.compose.composable.fake.AppDataStoreFake
import com.android.dev.engineer.kotlin.compose.data.use_case.mark_intro.MarkIntroCompleteUseCase
import com.android.dev.engineer.kotlin.compose.data.use_case.mark_intro.MarkIntroCompleteUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MarkIntroCompleteUseCaseTest {
    @get:Rule
    val mainTestRule: MainTestRule = MainTestRule()
    private lateinit var appDataStore: AppDataStoreFake
    private lateinit var markIntroCompleteUseCase: MarkIntroCompleteUseCase

    @Before
    fun setUp() {
        appDataStore = AppDataStoreFake()
        markIntroCompleteUseCase = MarkIntroCompleteUseCaseImpl(
            dispatcher = mainTestRule.testDispatcher,
            appDataStore = appDataStore
        )
    }

    @Test
    fun `test mark intro is updated`() = runTest {
        assertEquals(false, appDataStore.markIntroComplete)
        markIntroCompleteUseCase.invoke()
        assertEquals(true, appDataStore.markIntroComplete)
    }
}