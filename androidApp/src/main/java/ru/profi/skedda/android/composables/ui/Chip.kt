package ru.profi.skedda.android.composables.ui

import android.content.res.Resources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showBackground = true)
fun Chip(
    text: String = "Hello",
    isSelected: Boolean = true,
    onChipClicked: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onChipClicked.invoke() },
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(4.dp, color = MaterialTheme.colors.primary),
        color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    ) {
        Text(
            text = text,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
        )
    }
}