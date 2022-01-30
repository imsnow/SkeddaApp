package ru.profi.skedda.shared.featues.account

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.usecases.LoadAccountInfoUseCase

class AccountViewModel internal constructor(
    private val loadAccountInfoUseCase: LoadAccountInfoUseCase
) : ViewModel() {

    fun loadAccount() {
        viewModelScope.launch {
            val account = loadAccountInfoUseCase.loadAccount()
            println(">>> account $account")
        }
    }
}