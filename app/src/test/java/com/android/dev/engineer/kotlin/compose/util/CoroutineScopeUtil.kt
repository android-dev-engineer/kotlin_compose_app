package com.android.dev.engineer.kotlin.compose.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@ExperimentalCoroutinesApi
object CoroutineScopeUtil {
    fun createDataSourceCoroutineScope(): CoroutineScope {
        val testScope = TestScope(UnconfinedTestDispatcher())
        return CoroutineScope(testScope.coroutineContext + Job())
    }
}