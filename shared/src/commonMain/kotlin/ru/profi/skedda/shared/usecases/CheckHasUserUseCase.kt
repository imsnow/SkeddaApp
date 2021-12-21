package ru.profi.skedda.shared.usecases

import ru.profi.skedda.shared.Storage
import ru.profi.skedda.shared.data.internal.Credential
import ru.profi.skedda.shared.network.SkeddaApi

class CheckHasUserUseCase internal constructor(
    private val api: SkeddaApi,
    private val storage: Storage
) {

    fun checkUser(): Credential? {
        return storage.loadCredential()
    }
}