package ru.profi.skedda.network

import io.ktor.client.*
import io.ktor.client.features.cookies.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.profi.skedda.network.login.LoginPayload
import ru.profi.skedda.network.login.LoginRequestPayload

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
    suspend fun login(email: String, password: String): String {
        val url = Url(LOGIN_HOST)
        val loginData = LoginRequestPayload(
            LoginPayload(username = email, password = password)
        )
        val result = client.post<String>(url) {
            contentType(ContentType.Application.Json)
            body = loginData
        }
        println(">>> login result $result")
        return result
    }

    @Throws(Exception::class)
    suspend fun webs() {
        val url = Url("https://ruwinmike.skedda.com/webs")
        val result = client.get<String>(url)
        println(">>> webs result $result")
    }

    internal companion object {

        private const val LOGIN_HOST = "https://app.skedda.com/logins"
    }
}