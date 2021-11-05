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

internal class SkeddaApi {

    private val formatter = ISO8601.DATETIME_COMPLETE

    private var tokenHandler: AuthTokenHandler? = null

    fun setTokenHandler(handler: AuthTokenHandler) {
        tokenHandler = handler
    }

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(KotlinJson {
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
        install(HttpCookies) {
            storage = AcceptAllCookiesStorage()
        }
    }

    private var retrieveToken: String? = null

    @Throws(Exception::class)
    suspend fun login(email: String, password: String): UserLogin {
        val url = Url("${MAIN_HOST}/logins")
        val loginData = LoginRequestPayload(
            LoginPayload(username = email, password = password)
        )
        val user = client.post<UserLogin>(url) {
            contentType(ContentType.Application.Json)
            body = loginData
        }
        tokenHandler?.onTokenReceived("hello")

        println(">>> user $user")
        return user
    }

    // FIXME: 01.11.2021 подумать о нормальной реализации хранения
    @Throws(Exception::class)
    private suspend fun retrieveVerificationToken(force: Boolean = false): String {
        var token = retrieveToken?.takeIf { !force }

        if (token !== null) {
            return token
        }

        val url = Url("${USER_HOST}/booking")
        val html = client.get<String>(url)

        // FIXME: 01.11.2021 сделать нормальный парсинг
        val pattern = """<input[^>]+name="__RequestVerificationToken"[^>]+value="(.*?)"""".toRegex()

        // FIXME: 01.11.2021 сделать нормальную ошибку
        token = pattern.find(html)?.destructured?.component1() ?: throw Exception("No request verification token in html");

        this.retrieveToken = token

        return token
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
        val verificationToken = this.retrieveVerificationToken();
        val url = Url("${USER_HOST}/booking")

        val result = client.get<String>(url) {
            header(KEY_TOKEN_HEADER, verificationToken)
        }
        println(">>> booking $result")
    }

    suspend fun bookingList(start: Long, end: Long): BookingLists {
        val verificationToken = this.retrieveVerificationToken();
        val url = Url("${USER_HOST}/bookingslists")

        val startDate = formatter.format(start)
        println(">>> start date $startDate")

        val bookingList = client.get<BookingLists>(url) {
            header(KEY_TOKEN_HEADER, verificationToken)

            parameter("start", startDate)
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