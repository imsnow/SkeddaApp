package ru.profi.skedda.android

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import ru.profi.skedda.android.composables.*
import ru.profi.skedda.shared.data.BookingDuration
import ru.profi.skedda.shared.data.BookingDuration.Companion.fromMillis
import ru.profi.skedda.shared.featues.booking.BookingContext
import ru.profi.skedda.shared.router.Router
import ru.profi.skedda.shared.router.Screen

@ExperimentalMaterialApi
class ComposeRouter() : Router {

    private lateinit var navController: NavController
    private lateinit var bottomSheetState: BottomSheetScaffoldState
    private lateinit var coroutineScope: CoroutineScope

    override val initScreen: Screen = Screen.Main

    fun initWithActivity(componentActivity: ComponentActivity) {
        componentActivity.setContent {
            val navController = rememberNavController()
            val bottomSheetState = rememberBottomSheetScaffoldState(
                bottomSheetState = rememberBottomSheetState(
                    initialValue = BottomSheetValue.Collapsed
                )
            )
            coroutineScope = rememberCoroutineScope()
            NavHost(
                navController = navController,
                startDestination = initScreen.route
            ) {
                composable(Screen.Main.route) {
                    MainScreen(bottomSheetState) {
                        BottomSheetScreen()
                    }
                }
                composable(Screen.Login.route) { LoginScreen() }
                composable(Screen.Schedule.route) { ScheduleScreen() }
                composable(Screen.Booking.route) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")
                    val from = backStackEntry.arguments?.getString("from")
                    val duration = backStackEntry.arguments?.getString("duration")
                    val context = BookingContext(
                        spaceId = id!!.toLong(),
                        from = from!!.toLong(),
                        duration = duration!!.fromMillis()
                    )
                    BookingScreen(context = context)
                }
                composable(Screen.Account.route) { AccountScreen() }
            }
            this.navController = navController
            this.bottomSheetState = bottomSheetState
        }
    }

    override fun goToSchedule() {
        navController.navigate(Screen.Schedule.route)
    }

    override fun goToLogin() {
        navController.navigate(Screen.Login.route)
    }

    override fun showBooking(id: Long, from: Long, duration: BookingDuration) {
        navController.navigate(Screen.Booking.routeTo(id, from, duration))
    }

    override fun goToAccount() {
        navController.navigate(Screen.Account.route)
    }
}
