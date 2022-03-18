package ru.profi.skedda.shared.validators

object PasswordValidator {

    fun validate(password: String) = password.isNotEmpty()
}
