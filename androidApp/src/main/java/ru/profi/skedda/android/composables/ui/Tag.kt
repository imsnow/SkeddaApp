package ru.profi.skedda.android.composables.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DsTag(
    modifier: Modifier = Modifier,
    text: String = "Hello",
    isSelected: Boolean = true,
    onTagClicked: () -> Unit = {}
) {
    Surface(
        shape = RoundedCornerShape(25.dp),
        onClick = onTagClicked,
        color = if (isSelected) ProfiTheme.textBlack else ProfiTheme.buttonGrey,
        indication = rememberRipple()
    ) {
        LRegularText(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 13.dp),
            text = text,
            textColor = if (isSelected) ProfiTheme.white else ProfiTheme.textBlack
        )
    }
}