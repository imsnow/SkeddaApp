package ru.profi.skedda.shared.usecases

import ru.profi.skedda.shared.Storage

class LoadAccountInfoUseCase internal constructor(
    private val storage: Storage
) {

    suspend fun loadAccount() = storage.loadAccount()
}
