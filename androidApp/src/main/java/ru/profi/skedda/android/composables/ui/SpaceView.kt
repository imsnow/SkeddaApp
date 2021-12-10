package ru.profi.skedda.android.composables.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SpaceView(id: Long, name: String, onClick: (Long) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth().clickable { onClick.invoke(id) },
        elevation = 4.dp
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = name,
            fontSize = 28.sp
        )
    }
}