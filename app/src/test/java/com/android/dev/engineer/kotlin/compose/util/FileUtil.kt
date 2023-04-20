package com.android.dev.engineer.kotlin.compose.util

object FileUtil {
    fun getContent(file: String): String {
        return javaClass.classLoader?.getResource(file)!!.readText()
    }
}