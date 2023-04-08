package com.android.dev.engineer.kotlin.compose.viewmodel

import com.android.dev.engineer.kotlin.compose.coroutines.MainTestRule
import com.android.dev.engineer.kotlin.compose.fake.GetInitialRouteUseCaseFake
import com.android.dev.engineer.kotlin.compose.data.domain.MainNavGraph
import com.android.dev.engineer.kotlin.compose.feature.routing.RoutingViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RoutingViewModelTest {
    @get:Rule
    val mainTestRule: MainTestRule = MainTestRule()
    private lateinit var getInitialRouteUseCase: GetInitialRouteUseCaseFake
    private lateinit var viewModel: RoutingViewModel

    @Before
    fun setUp() {
        getInitialRouteUseCase = GetInitialRouteUseCaseFake()
        viewModel = RoutingViewModel(getInitialRouteUseCase = getInitialRouteUseCase)
    }

    @Test
    fun `test initial navigation as intro`() = runTest {
        val expectedNavGraph = MainNavGraph.Intro
        getInitialRouteUseCase.mainNavGraph = expectedNavGraph
        assertEquals(expectedNavGraph, viewModel.effect.first())
    }

    @Test
    fun `test initial navigation as sign in`() = runTest {
        val expectedNavGraph = MainNavGraph.SignIn
        getInitialRouteUseCase.mainNavGraph = expectedNavGraph
        assertEquals(expectedNavGraph, viewModel.effect.first())
    }

    @Test
    fun `test when initial navigation fails and redirect to sign in`() = runTest {
        getInitialRouteUseCase.error = Exception()
        assertEquals(MainNavGraph.SignIn, viewModel.effect.first())
    }
}