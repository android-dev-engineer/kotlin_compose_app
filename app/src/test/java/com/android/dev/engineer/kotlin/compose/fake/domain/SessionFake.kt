package com.android.dev.engineer.kotlin.compose.fake.domain

import com.android.dev.engineer.kotlin.compose.data.domain.network.NewSession
import com.android.dev.engineer.kotlin.compose.data.domain.network.Session

object SessionFake {
    fun createSession() = Session(
        requestToken = "abcd"
    )

    fun createNewSession() = NewSession(
        sessionId = "1234"
    )
}