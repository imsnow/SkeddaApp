package ru.profi.skedda.android

import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.shared.featues.login.LoginViewModel

@Composable
fun LoginScreen() {
    val viewModel: LoginViewModel = getViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = viewModel.testText(),
            fontSize = 32.sp
        )
    }
}