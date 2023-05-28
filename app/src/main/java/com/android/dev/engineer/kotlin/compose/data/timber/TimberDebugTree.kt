package com.android.dev.engineer.kotlin.compose.data.timber

import timber.log.Timber

class TimberDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "(${element.fileName}:${element.lineNumber})"
    }
}