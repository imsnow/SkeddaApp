package ru.profi.skedda.shared.featues.login

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.router.Router
import ru.profi.skedda.shared.usecases.LoginUseCase
import ru.profi.skedda.shared.validators.EmailValidator
import ru.profi.skedda.shared.validators.PasswordValidator

class LoginViewModel internal constructor(
    private val loginUseCase: LoginUseCase,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val router: Router
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
            loginUseCase.login(
                email = state.value.email,
                password = state.value.password
            )
            router.goToSchedule()
        }
    }
}
