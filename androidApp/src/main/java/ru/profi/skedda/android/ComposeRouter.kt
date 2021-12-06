package ru.profi.skedda.android

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.profi.skedda.android.composables.LoginScreen
import ru.profi.skedda.android.composables.MainScreen
import ru.profi.skedda.android.composables.ScheduleScreen
import ru.profi.skedda.shared.events.Event
import ru.profi.skedda.shared.events.EventHandler
import ru.profi.skedda.shared.events.EventsDispatcher
import ru.profi.skedda.shared.events.MainEvent
import ru.profi.skedda.shared.router.Router
import ru.profi.skedda.shared.router.Screen

@ExperimentalMaterialApi
class ComposeRouter(private val eventHandler: EventHandler) : Router, EventsDispatcher {

    private lateinit var navController: NavController

    override val initScreen: Screen = Screen.Main

    fun initWithActivity(componentActivity: ComponentActivity) {
        componentActivity.setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = initScreen.route
            ) {
                composable(Screen.Main.route) { MainScreen() }
                composable(Screen.Login.route) { LoginScreen() }
                composable(Screen.Schedule.route) { ScheduleScreen() }
            }
            this.navController = navController
        }
    }

    override fun goToSchedule() {
        navController.navigate(Screen.Schedule.route)
    }

    override fun goToLogin() {
        navController.navigate(Screen.Login.route)
    }

    override fun showBooking(id: Long) {
//        navController.navigate(Screen.Booking.route)
        eventHandler.handleEvent(event = MainEvent.ShowBooking)
    }

    override fun dispatchEvent(event: Event) {
        eventHandler.handleEvent(event = event)
    }
}