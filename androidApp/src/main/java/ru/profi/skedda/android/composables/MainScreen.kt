package ru.profi.skedda.android.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.shared.featues.main.LoginType
import ru.profi.skedda.shared.featues.main.MainViewModel

@ExperimentalMaterialApi
@Composable
fun MainScreen(
    bottomSheetState: BottomSheetScaffoldState,
    sheetContent: @Composable ColumnScope.() -> Unit
) {
    val mainViewModel: MainViewModel = getViewModel()
    val state = mainViewModel.state.collectAsState()

    BottomSheetScaffold(
        sheetContent = sheetContent,
        scaffoldState = bottomSheetState,
        sheetPeekHeight = 0.dp // TODO may be will need
    ) {
        when (state.value.type) {
            LoginType.NEED_LOGIN -> LoginScreen()
            LoginType.HAS_USER -> ScheduleScreen()
            LoginType.PREPARING -> Unit
        }
    }
}