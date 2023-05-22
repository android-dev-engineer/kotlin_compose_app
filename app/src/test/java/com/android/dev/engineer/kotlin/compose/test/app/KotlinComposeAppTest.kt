package com.android.dev.engineer.kotlin.compose.test.app

import com.android.dev.engineer.kotlin.compose.app.KotlinComposeApp
import com.android.dev.engineer.kotlin.compose.data.timber.TimberDebugTree
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import timber.log.Timber

@RunWith(RobolectricTestRunner::class)
class KotlinComposeAppTest {
    lateinit var kotlinComposeApp: KotlinComposeApp

    @Before
    fun setUp() {
        kotlinComposeApp = KotlinComposeApp()
    }

    @Test
    fun testKotlinCompose() {
        kotlinComposeApp.onCreate()
        assertEquals(1, Timber.treeCount)
        assertTrue(Timber.forest().first() is TimberDebugTree)
    }
}