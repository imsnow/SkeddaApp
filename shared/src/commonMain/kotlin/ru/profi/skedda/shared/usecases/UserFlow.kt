package ru.profi.skedda.shared.usecases

enum class UserFlow {

    REGISTRATION,
}

sealed interface Screen {

    val userFlow: UserFlow

    class ForceLogin(uf: UserFlow) : Screen {
        override val userFlow: UserFlow = uf
    }

    object Name : Screen {
        override val userFlow: UserFlow
            get() = UserFlow.REGISTRATION
    }
}

sealed interface Action {
    val screen: Screen

    class ByPhone(uf: UserFlow) : Action {
        override val screen: Screen = Screen.ForceLogin(uf)
    }

    object VerifyName : Action {
        override val screen: Screen = Screen.Name
    }
}

fun passError(action: Action, e: Throwable) {
    println(">>> action $action")
    println("screen ${action.screen}")
    println("userFlow ${action.screen.userFlow}")
}

fun onError(e: Throwable) {
    val action = Action.ByPhone(UserFlow.REGISTRATION)
    passError(action, e)

    passError(action = Action.VerifyName, e = e)
}