package ru.profi.skedda.android.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.shared.featues.booking.BookingContext
import ru.profi.skedda.shared.featues.booking.BookingViewModel

@Composable
fun BookingScreen(context: BookingContext) {
    val viewModel: BookingViewModel = getViewModel()
    viewModel.loadSpace(context)
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
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = state.value.spaceName,
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = state.value.fromTo,
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = state.value.isSpaceLoaded,
            onClick = { viewModel.goBook() }
        ) {
            Text(text = "Забронировать")
        }
    }
}