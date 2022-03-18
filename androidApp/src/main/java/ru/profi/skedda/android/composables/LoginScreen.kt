package ru.profi.skedda.android.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.android.composables.ui.*
import ru.profi.skedda.shared.featues.login.LoginViewModel

@Composable
fun LoginScreen() {
    val viewModel: LoginViewModel = getViewModel()
    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        TitleText(text = "Войдите в свою учётную запись")
        Spacer(modifier = Modifier.height(20.dp))
        DsTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.value.email,
            onValueChange = { viewModel.emailChange(it) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        DsTextField(
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            value = state.value.password,
            onValueChange = { viewModel.onPasswordChanged(it) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        DsButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Войти",
            color = Color.BLACK,
            enabled = state.value.isActiveButton,
            onClick = { viewModel.login() }
        )
    }
}
