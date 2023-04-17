package com.android.dev.engineer.kotlin.compose.extension

import com.android.dev.engineer.kotlin.compose.data.di.NetworkModule

object MoshiAdapterExt {
    inline fun <reified T> T.toJson(): String {
        return NetworkModule.provideMoshi().adapter(T::class.java).toJson(this)
    }

    inline fun <reified T> String.fromJson(): T {
        return NetworkModule.provideMoshi().adapter(T::class.java).fromJson(this)!!
    }
}