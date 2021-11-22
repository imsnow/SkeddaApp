package ru.profi.skedda.android.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.shared.featues.schedule.ScheduleViewModel
import ru.profi.skedda.shared.repositories.FreeSpace

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
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = state.value.time,
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        )
        Spaces(list = state.value.spaces)
    }
}

@Composable
private fun Spaces(list: List<FreeSpace>) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) { space ->
            SpaceView(name = space.name)
        }
    }
}

@Composable
private fun SpaceView(name: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = name,
            fontSize = 28.sp
        )
    }
}