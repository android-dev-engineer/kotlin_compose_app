package com.android.dev.engineer.kotlin.compose.test.extension

import com.android.dev.engineer.kotlin.compose.extension.toDateFormatted
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class StringExtTest {
    @Test
    fun `test date formatted`() {
        val date = "2023-04-05"
        val formattedData = date.toDateFormatted(locale = Locale.ENGLISH)
        assertEquals("April 5, 2023", formattedData)
    }

    @Test
    fun `test invalid date`() {
        val date = "2023/04/05"
        val formattedData = date.toDateFormatted(locale = Locale.ENGLISH)
        assertEquals("", formattedData)
    }
}