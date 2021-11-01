package ru.profi.skedda.shared.network

import io.ktor.client.*
import io.ktor.client.features.cookies.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.profi.skedda.shared.network.login.LoginPayload
import ru.profi.skedda.shared.network.login.LoginRequestPayload

class SkeddaApi {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
        install(HttpCookies) {
            storage = AcceptAllCookiesStorage()
        }
    }

    @Throws(Exception::class)
    suspend fun login(email: String, password: String): Unit {
        val url = Url("${LOGIN_HOST}/logins")
        val loginData = LoginRequestPayload(
            LoginPayload(username = email, password = password)
        )

        client.post<String>(url) {
            contentType(ContentType.Application.Json)
            body = loginData
        }
    }

    @Throws(Exception::class)
    private suspend fun retrieveVerificationToken(): String {
        val url = Url("${USER_HOST}/booking")
        val html = client.get<String>(url);

        // FIXME: 01.11.2021 сделать нормальный парсинг
        val pattern = """<input[^>]+name="__RequestVerificationToken"[^>]+value="(.*?)"""".toRegex()

        // FIXME: 01.11.2021 сделать нормальную ошибку
        return pattern.find(html)?.destructured?.component1() ?: throw Exception("No request verification token in html");
    }

    @Throws(Exception::class)
    suspend fun webs() {
        val verificationToken = this.retrieveVerificationToken();

        val url = Url("${USER_HOST}/webs")
        val result = client.get<String>(url) {
            header("X-Skedda-RequestVerificationToken", verificationToken)
        }
        println(">>> webs result $result")
    }

    internal companion object {

        private const val LOGIN_HOST = "https://app.skedda.com"
        private const val USER_HOST = "https://ruwinmike.skedda.com"
    }
}