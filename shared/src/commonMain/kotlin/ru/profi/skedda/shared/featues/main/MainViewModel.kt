package ru.profi.skedda.shared.featues.main

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import ru.profi.skedda.shared.repositories.UserRepository

class MainViewModel internal constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            userRepository.loadUser()
        }
    }
}