package com.android.dev.engineer.kotlin.compose.extension

import com.android.dev.engineer.kotlin.compose.data.di.NetworkModule.provideMoshi

object MoshiAdapterExt {
    inline fun <reified T> T.toJson(): String {
        return provideMoshi().adapter(T::class.java).toJson(this)
    }

    inline fun <reified T> String.fromJson(): T {
        return provideMoshi().adapter(T::class.java).fromJson(this)!!
    }
}