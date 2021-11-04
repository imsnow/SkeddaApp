package ru.profi.skedda.android.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.shared.featues.schedule.ScheduleViewModel

@Composable
fun ScheduleScreen() {

    val viewModel: ScheduleViewModel = getViewModel()
    val state = viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = state.value.date,
            textAlign = TextAlign.Center
        )
        Text(
            text = state.value.time,
            textAlign = TextAlign.Center
        )
    }
}