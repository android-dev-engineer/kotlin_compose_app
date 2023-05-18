package com.android.dev.engineer.kotlin.compose.extension

import com.android.dev.engineer.kotlin.compose.data.api.TheMovieApi.Companion.DEFAULT_DATE_PATTERN
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDateFormatted(locale: Locale = Locale.getDefault()): String {
    try {
        val date = SimpleDateFormat(DEFAULT_DATE_PATTERN, locale).parse(this)
        if (date != null) {
            return SimpleDateFormat.getDateInstance(DateFormat.LONG, locale).format(date)
        }
    } catch (_: ParseException) {
        // TODO add log
    }
    return ""
}