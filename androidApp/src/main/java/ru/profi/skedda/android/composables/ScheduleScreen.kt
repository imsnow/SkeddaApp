package ru.profi.skedda.android.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import ru.profi.skedda.android.composables.ui.*
import ru.profi.skedda.shared.data.BookingDuration
import ru.profi.skedda.shared.featues.schedule.ScheduleViewModel
import ru.profi.skedda.shared.data.FreeSpace

@Composable
fun ScheduleScreen() {

    val viewModel: ScheduleViewModel = getViewModel()
    val state = viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    HeadlineText(text = "Расписание")
                },
                backgroundColor = ProfiTheme.white,
                actions = {
                    IconButton(
                        onClick = { viewModel.onAccountClicked() }
                    ) {
                        BadgedBox(badge = {
                            if (state.value.accountBookingCount > 0) {
                                Badge {
                                    Text(text = state.value.accountBookingCount.toString())
                                }
                            }
                        }) {
                            Icon(Icons.Rounded.AccountCircle, contentDescription = "Close")
                        }
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LRegularText(text = "Выберите время и свободную переговорку")
            Spacer(modifier = Modifier.height(20.dp))
            TitleText(
                modifier = Modifier.fillMaxWidth(),
                text = state.value.date,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            TimeChangerView(
                text = state.value.time,
                isMinusEnabled = true,
                isPlusEnabled = true,
                onMinusClicked = {
                    viewModel.minusTime()
                },
                onPlusClicked = {
                    viewModel.plusTime()
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
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
            DsTag(
                text = item.title,
                isSelected = item == selectedDuration,
                onTagClicked = {
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

//@Composable
//fun AppBarIcon() {
//    Container(width = ActionIconDiameter, height = ActionIconDiameter) {
//        Ripple(bounded = false) {
//            Clickable(onClick = onClick) {
//                SimpleImage(icon)
//            }
//        }
//    }
//}