package ru.profi.skedda.shared.featues.account

import com.soywiz.klock.DateTime
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.usecases.LoadAccountInfoUseCase
import ru.profi.skedda.shared.usecases.LoadMyBookingsUseCase

class AccountViewModel internal constructor(
    private val loadAccountInfoUseCase: LoadAccountInfoUseCase,
    private val loadMyBookingsUseCase: LoadMyBookingsUseCase
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
            val now = DateTime.now()
            val accountBookings = loadMyBookingsUseCase.loadMyBookings(
                fromDate = now.unixMillisLong,
                venueUserId = account.id
            )
            println(">>> account bookings $accountBookings")
        }
    }

    fun onLogoutClicked() {
        println(">>> logout clicked")
    }
}
