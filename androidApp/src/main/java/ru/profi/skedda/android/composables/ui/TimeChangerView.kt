package ru.profi.skedda.android.composables.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showBackground = true)
fun TimeChangerView(
    text: String = "10:05",
    isMinusEnabled: Boolean = true,
    isPlusEnabled: Boolean = true,
    onMinusClicked: () -> Unit = {},
    onPlusClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DsButton(
            title = "- 30 минут",
            color = Color.GREY,
            enabled = isMinusEnabled,
            onClick = onMinusClicked
        )
        TitleText(
            text = text,
            modifier = Modifier.width(100.dp),
            maxLines = 1,
            textAlign = TextAlign.Center
        )
        DsButton(
            title = "+ 30 минут",
            enabled = isPlusEnabled,
            onClick = onPlusClicked
        )
    }
}