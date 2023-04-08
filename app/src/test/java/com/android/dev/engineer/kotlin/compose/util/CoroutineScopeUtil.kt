package com.android.dev.engineer.kotlin.compose.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.TestScope

@ExperimentalCoroutinesApi
object CoroutineScopeUtil {
    fun createDataSourceCoroutineScope(testScope: TestScope): CoroutineScope {
        return CoroutineScope(testScope.coroutineContext + Job())
    }
}