package ru.profi.skedda.shared.network

import io.ktor.client.*
import io.ktor.client.features.cookies.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json as KoltinJson

internal class NetworkClient {

    val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(KoltinJson {
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
}