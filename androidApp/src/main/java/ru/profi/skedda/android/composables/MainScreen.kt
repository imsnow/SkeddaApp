package ru.profi.skedda.android.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.shared.featues.main.MainViewModel

@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    val mainViewModel: MainViewModel = getViewModel()
    val state = mainViewModel.state.collectAsState()
    BottomSheetScaffold(
        sheetContent = { BookingScreen() },
        sheetPeekHeight = 2.dp
    ) {
        println(">>> need login ${state.value.needLogin}")
        if (state.value.needLogin) {
            LoginScreen()
        } else {
            ScheduleScreen()
        }
    }
//    if (state.value.needLogin) {
//        LaunchedEffect(key1 =, block =)
//    }
}