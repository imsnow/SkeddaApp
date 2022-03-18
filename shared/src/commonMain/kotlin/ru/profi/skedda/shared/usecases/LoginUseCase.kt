package ru.profi.skedda.shared.usecases

import ru.profi.skedda.shared.Storage
import ru.profi.skedda.shared.data.internal.Credential
import ru.profi.skedda.shared.network.SkeddaApi

class LoginUseCase internal constructor(
    private val api: SkeddaApi,
    private val storage: Storage
) {

    suspend fun login(email: String, password: String) {
        val userLogin = api.login(email, password)
        println(">>> login success ${userLogin.login}")
        val credential = Credential(email = email, password = password)
        storage.saveCredentials(credential)
    }
}
