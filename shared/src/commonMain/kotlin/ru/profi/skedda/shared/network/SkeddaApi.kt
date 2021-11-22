package ru.profi.skedda.shared.network

import com.soywiz.klock.ISO8601
import com.soywiz.klock.format
import io.ktor.client.*
import io.ktor.client.features.cookies.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.profi.skedda.shared.network.login.LoginPayload
import ru.profi.skedda.shared.network.login.LoginRequestPayload
import ru.profi.skedda.shared.data.internal.BookingLists
import ru.profi.skedda.shared.data.internal.Webs
import ru.profi.skedda.shared.repositories.UserLogin
import kotlinx.serialization.json.Json as KotlinJson

internal class SkeddaApi(networkClient: NetworkClient) {

    private val formatter = ISO8601.DATETIME_COMPLETE

    private var token: String? = null

    private val client = networkClient.client

    @Throws(Exception::class)
    suspend fun login(email: String, password: String): UserLogin {
        val url = Url("${MAIN_HOST}/logins")
        val loginData = LoginRequestPayload(
            LoginPayload(
                username = email,
                password = password,
                rememberMe = false,
                arbitraryerrors = null
            )
        )
        val user = client.post<UserLogin>(url) {
            contentType(ContentType.Application.Json)
            body = loginData
        }

        return user
    }

    @Throws(Exception::class)
    private suspend fun retrieveVerificationToken(): String {
        return token ?: requestVerificationToken().also { token = it }
    }

    private suspend fun requestVerificationToken(): String {
        val url = Url("${USER_HOST}/booking")
        val html = client.get<String>(url)

        // FIXME: 01.11.2021 сделать нормальный парсинг
        val pattern = """<input[^>]+name="__RequestVerificationToken"[^>]+value="(.*?)"""".toRegex()

        // FIXME: 01.11.2021 сделать нормальную ошибку
        return pattern.find(html)?.destructured?.component1()
            ?: throw Exception("No request verification token in html")
    }

    @Throws(Exception::class)
    suspend fun webs(): Webs {
        val verificationToken = retrieveVerificationToken();

        val url = Url("${USER_HOST}/webs")
        return client.get(url) {
            header(KEY_TOKEN_HEADER, verificationToken)
        }
    }

    suspend fun booking() {
        val verificationToken = retrieveVerificationToken();
        val url = Url("${USER_HOST}/booking")

        val result = client.get<String>(url) {
            header(KEY_TOKEN_HEADER, verificationToken)
        }
        println(">>> booking $result")
    }

    suspend fun bookingList(start: Long, end: Long): BookingLists {
        val verificationToken = this.retrieveVerificationToken();
        val url = Url("${USER_HOST}/bookingslists")

        val bookingList = client.get<BookingLists>(url) {
            header(KEY_TOKEN_HEADER, verificationToken)

            parameter("start", formatter.format(start))
            parameter("end", formatter.format(end))
        }

        return bookingList
    }


    internal companion object {

        private const val MAIN_HOST = "https://app.skedda.com"
        private const val USER_HOST = "https://ruwinmike.skedda.com"

        private const val KEY_TOKEN_HEADER = "X-Skedda-RequestVerificationToken"
    }
}