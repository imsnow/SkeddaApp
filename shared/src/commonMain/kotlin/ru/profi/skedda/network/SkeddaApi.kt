package ru.profi.skedda.network

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.profi.skedda.network.login.LoginPayload
import ru.profi.skedda.network.login.LoginRequestPayload

class SkeddaApi {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun login(email: String, password: String): String {
        val url = Url(LOGIN_HOST)
        val loginData = LoginRequestPayload(
            LoginPayload(username = email, password = password)
        )
        val result = client.post<String>(url) {
            contentType(ContentType.Application.Json)
            body = loginData
        }
        println(">>> result $result")
        return result
    }

    internal companion object {

        private const val LOGIN_HOST = "https://app.skedda.com/logins"
    }
}