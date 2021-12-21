package ru.profi.skedda.android.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.shared.featues.booking.BookingViewModel

@Composable
fun BookingScreen(id: Long) {
    val viewModel: BookingViewModel = getViewModel()
    viewModel.loadSpace(id)
    val state = viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Бронируем?",
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = state.value.spaceName,
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        )
    }
}