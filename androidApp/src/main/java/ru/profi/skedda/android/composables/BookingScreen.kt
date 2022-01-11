package ru.profi.skedda.android.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.android.composables.ui.Color
import ru.profi.skedda.android.composables.ui.DsButton
import ru.profi.skedda.android.composables.ui.HeadlineText
import ru.profi.skedda.android.composables.ui.TitleText
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
            .padding(25.dp)
    ) {
        TitleText(
            modifier = Modifier.fillMaxWidth(),
            text = "Бронируем?",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        HeadlineText(
            modifier = Modifier.fillMaxWidth(),
            text = state.value.spaceName,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = state.value.fromTo,
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        DsButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Забронировать",
            color = Color.BLACK,
            enabled = state.value.isSpaceLoaded,
            onClick = { viewModel.goBook(context = context) }
        )
        Spacer(modifier = Modifier.height(10.dp))
        DsButton(
            modifier = Modifier
                .fillMaxWidth(),
            title = "Отмена",
            color = Color.GREY,
            enabled = state.value.isSpaceLoaded,
            onClick = { viewModel.cancel() }
        )
    }
}