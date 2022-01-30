package ru.profi.skedda.shared.featues.account

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.featues.booking.BookingViewState
import ru.profi.skedda.shared.usecases.LoadAccountInfoUseCase

class AccountViewModel internal constructor(
    private val loadAccountInfoUseCase: LoadAccountInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AccountViewState())
    val state: StateFlow<AccountViewState>
        get() = _state

    fun loadAccount() {
        viewModelScope.launch {
            val account = loadAccountInfoUseCase.loadAccount()
            println(">>> account $account")
            _state.value = state.value.copy(
                email = account.username,
                name = "${account.firstName} ${account.lastName}"
            )
        }
    }

    fun onLogoutClicked() {
        println(">>> logout clicked")
    }
}