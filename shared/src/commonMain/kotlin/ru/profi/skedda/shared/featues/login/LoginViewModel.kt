package ru.profi.skedda.shared.featues.login

import com.soywiz.klock.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.features.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.network.SkeddaApi
import ru.profi.skedda.shared.validators.EmailValidator
import ru.profi.skedda.shared.validators.PasswordValidator

class LoginViewModel(
    private val api: SkeddaApi,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) : ViewModel() {

    private val _state = MutableStateFlow(LoginViewState())
    val state: StateFlow<LoginViewState>
        get() = _state

    private val ceh = CoroutineExceptionHandler { _, throwable ->
        println(">>> exception $throwable")
    }

    fun emailChange(newValue: String) {
        val isEmailValid = emailValidator.validate(newValue)
        _state.value = _state.value.copy(email = newValue, isActiveButton = isEmailValid)
    }

    fun onPasswordChanged(newValue: String) {
        val isPasswordValid = passwordValidator.validate(newValue)
        _state.value = _state.value.copy(password = newValue, isActiveButton = isPasswordValid)
    }

    fun login() {
        viewModelScope.launch(ceh) {
            api.login(
                email = state.value.email,
                password = state.value.password
            )

            val spaces = api.webs()
            println(">>> spaces $spaces")
//            api.booking()

            val now = DateTime.now()

            val list = api.bookingList(
                start = now.unixMillisLong,
                end = (now + 1.days).unixMillisLong,
            )
            println(">>> list $list")
        }
    }
}