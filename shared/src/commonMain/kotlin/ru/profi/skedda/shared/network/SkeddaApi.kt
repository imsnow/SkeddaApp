package ru.profi.skedda.shared.network

import com.soywiz.klock.DateFormat
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
import ru.profi.skedda.shared.repositories.skedda.internal.BookingLists
import ru.profi.skedda.shared.repositories.skedda.internal.Webs
import kotlinx.serialization.json.Json as KotlinJson

class SkeddaApi {
    private val formatter = DateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

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

    private var retrieveToken: String? = null;

    @Throws(Exception::class)
    suspend fun login(email: String, password: String): Unit {
        val url = Url("${MAIN_HOST}/logins")
        val loginData = LoginRequestPayload(
            LoginPayload(username = email, password = password)
        )

        client.post<String>(url) {
            contentType(ContentType.Application.Json)
            body = loginData
        }
    }

    // FIXME: 01.11.2021 подумать о нормальной реализации хранения
    @Throws(Exception::class)
    private suspend fun retrieveVerificationToken(force: Boolean = false): String {
        var token = this.retrieveToken?.takeIf { !force };

        if (token !== null) {
            return token;
        }

        val url = Url("${USER_HOST}/booking")
        val html = client.get<String>(url);

        // FIXME: 01.11.2021 сделать нормальный парсинг
        val pattern = """<input[^>]+name="__RequestVerificationToken"[^>]+value="(.*?)"""".toRegex()

        // FIXME: 01.11.2021 сделать нормальную ошибку
        token = pattern.find(html)?.destructured?.component1() ?: throw Exception("No request verification token in html");

        this.retrieveToken = token

        return token;
    }

    @Throws(Exception::class)
    suspend fun webs(): Webs {
        val verificationToken = this.retrieveVerificationToken();

        val url = Url("${USER_HOST}/webs")
        return client.get<Webs>(url) {
            header("X-Skedda-RequestVerificationToken", verificationToken)
        }
    }

    suspend fun bookingList(start: Long, end: Long): BookingLists {
        val verificationToken = this.retrieveVerificationToken();

        val urlBuilder = URLBuilder("${USER_HOST}/bookingList")
        with(urlBuilder.parameters) {
            set("start", formatter.format(start))
            set("end", formatter.format(end))
        }

        return client.get<BookingLists>(urlBuilder.build()) {
            header("X-Skedda-RequestVerificationToken", verificationToken)
        }
    }



    internal companion object {

        private const val MAIN_HOST = "https://app.skedda.com"
        private const val USER_HOST = "https://ruwinmike.skedda.com"
    }
}