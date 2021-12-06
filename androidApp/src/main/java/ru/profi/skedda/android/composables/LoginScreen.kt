package ru.profi.skedda.android.composables

import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.shared.di.HH
import ru.profi.skedda.shared.events.EventsDispatcher
import ru.profi.skedda.shared.featues.login.LoginViewModel

@Composable
fun LoginScreen() {
    val viewModel: LoginViewModel = getViewModel()
    val state = viewModel.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "Войдите в свою учётную запись",
            fontSize = 32.sp
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = state.value.email,
            onValueChange = { viewModel.emailChange(it) }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            visualTransformation = PasswordVisualTransformation(),
            value = state.value.password,
            onValueChange = { viewModel.onPasswordChanged(it) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = state.value.isActiveButton,
            onClick = { viewModel.login() }
        ) {
            Text(text = "Войти")
        }
    }
}