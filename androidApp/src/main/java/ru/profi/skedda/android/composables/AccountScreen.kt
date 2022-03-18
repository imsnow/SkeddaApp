package ru.profi.skedda.android.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.android.composables.ui.Color
import ru.profi.skedda.android.composables.ui.DsButton
import ru.profi.skedda.android.composables.ui.HeadlineText
import ru.profi.skedda.android.composables.ui.TitleText
import ru.profi.skedda.shared.featues.account.AccountViewModel

@Preview
@Composable
fun AccountScreen() {
    val viewModel: AccountViewModel = getViewModel()
    val state = viewModel.state.collectAsState()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.loadAccount()
    })
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
    ) {
        TitleText(text = "Привет,\n${state.value.name}")
        Spacer(modifier = Modifier.height(10.dp))
        HeadlineText(text = state.value.email)
        Spacer(modifier = Modifier.height(20.dp))
        DsButton(
            title = "Выйти",
            color = Color.GREY,
            enabled = true,
            onClick = {
                viewModel.onLogoutClicked()
            }
        )
    }
}
