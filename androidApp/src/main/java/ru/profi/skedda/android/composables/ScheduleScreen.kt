package ru.profi.skedda.android.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.android.composables.ui.Chip
import ru.profi.skedda.android.composables.ui.SpaceView
import ru.profi.skedda.android.composables.ui.TimeChangerView
import ru.profi.skedda.shared.data.BookingDuration
import ru.profi.skedda.shared.featues.schedule.ScheduleViewModel
import ru.profi.skedda.shared.data.repositories.FreeSpace

@Composable
fun ScheduleScreen() {

    val viewModel: ScheduleViewModel = getViewModel()
    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = state.value.date,
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        )
        TimeChangerView(text = state.value.time) {
            viewModel.plusTime()
        }
        DurationsView(
            list = state.value.durations,
            selectedDuration = state.value.selectedDuration
        ) { bookingDuration ->
            viewModel.selectDuration(bookingDuration)
        }
        Spaces(list = state.value.spaces) { id ->
            viewModel.onSpaceClicked(id)
        }
    }
}

@Composable
private fun DurationsView(
    list: List<BookingDuration>,
    selectedDuration: BookingDuration,
    onSelectDuration: (BookingDuration) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) { item ->
            Chip(
                text = item.title,
                isSelected = item == selectedDuration,
                onChipClicked = {
                    onSelectDuration.invoke(item)
                }
            )
        }
    }
}

@Composable
private fun Spaces(list: List<FreeSpace>, onSpaceClick: (Long) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) { space ->
            SpaceView(id = space.id, name = space.name, onClick = onSpaceClick)
        }
    }
}