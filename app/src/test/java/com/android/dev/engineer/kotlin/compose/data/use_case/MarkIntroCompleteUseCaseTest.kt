package com.android.dev.engineer.kotlin.compose.data.use_case

import com.android.dev.engineer.kotlin.compose.data.use_case.mark_intro.MarkIntroCompleteUseCase
import com.android.dev.engineer.kotlin.compose.data.use_case.mark_intro.MarkIntroCompleteUseCaseImpl
import com.android.dev.engineer.kotlin.compose.fake.AppDataStoreFake
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MarkIntroCompleteUseCaseTest {
    private lateinit var appDataStore: AppDataStoreFake
    private lateinit var markIntroCompleteUseCase: MarkIntroCompleteUseCase

    @Before
    fun setUp() {
        appDataStore = AppDataStoreFake()
        markIntroCompleteUseCase = MarkIntroCompleteUseCaseImpl(
            dispatcher = UnconfinedTestDispatcher(),
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