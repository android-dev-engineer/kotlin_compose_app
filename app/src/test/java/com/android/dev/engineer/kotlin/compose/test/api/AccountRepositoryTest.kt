package com.android.dev.engineer.kotlin.compose.test.api

import com.android.dev.engineer.kotlin.compose.data.api.authentication.AuthenticationRepository
import com.android.dev.engineer.kotlin.compose.data.api.authentication.AuthenticationRepositoryImpl
import com.android.dev.engineer.kotlin.compose.data.domain.network.NewSession
import com.android.dev.engineer.kotlin.compose.data.domain.network.Session
import com.android.dev.engineer.kotlin.compose.extension.MoshiAdapterExt.fromJson
import com.android.dev.engineer.kotlin.compose.extension.MoshiAdapterExt.toJson
import com.android.dev.engineer.kotlin.compose.extension.toTheMovieApi
import com.android.dev.engineer.kotlin.compose.fake.domain.SessionFake.createNewSession
import com.android.dev.engineer.kotlin.compose.fake.domain.SessionFake.createSession
import com.android.dev.engineer.kotlin.compose.util.FileResourceUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class AccountRepositoryTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var authenticationRepository: AuthenticationRepository

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        authenticationRepository = AuthenticationRepositoryImpl(mockWebServer.toTheMovieApi())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `verify get new session url`() = runTest {
        val bodyResponse = createNewSession().toJson()
        val mockResponse = MockResponse().setBody(bodyResponse)
        mockWebServer.enqueue(mockResponse)
        authenticationRepository.getNewSession(session = createSession())

        val request = mockWebServer.takeRequest()
        val requestUrl = request.requestUrl!!
        assertEquals("POST", request.method)
        assertEquals("/authentication/session/new", requestUrl.encodedPath)
    }

    @Test
    fun `verify get new session request`() = runTest {
        val expectedSession = FileResourceUtil.getContent(file = "json/session.json").fromJson<Session>()
        val bodyResponse = FileResourceUtil.getContent(file = "json/new_session.json")
        val mockResponse = MockResponse().setBody(bodyResponse)
        mockWebServer.enqueue(mockResponse)
        authenticationRepository.getNewSession(session = expectedSession)

        val session = mockWebServer.takeRequest().body.readUtf8().fromJson<Session>()
        assertEquals(expectedSession, session)
    }

    @Test
    fun `verify get new session response`() = runTest {
        val bodyJson = FileResourceUtil.getContent(file = "json/new_session.json")
        val mockResponse = MockResponse().setBody(bodyJson)
        mockWebServer.enqueue(mockResponse)

        val expectedNewSession = bodyJson.fromJson<NewSession>()
        val newSession = authenticationRepository.getNewSession(session = createSession())
        assertEquals(expectedNewSession, newSession)
    }
}