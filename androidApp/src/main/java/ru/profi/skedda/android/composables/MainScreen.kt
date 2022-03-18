package ru.profi.skedda.android.composables

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
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
