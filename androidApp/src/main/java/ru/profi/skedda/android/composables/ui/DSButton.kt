package ru.profi.skedda.android.composables.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun DsButton(
    modifier: Modifier = Modifier,
    title: String = "Button",
    color: Color = Color.RED,
    enabled: Boolean = false,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(
            start = 15.dp,
            top = 13.dp,
            end = 15.dp,
            bottom = 15.dp
        ),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when (color) {
                Color.RED -> ProfiTheme.buttonRed
                Color.BLACK -> ProfiTheme.buttonBlack
                Color.GREY -> ProfiTheme.buttonGrey
                Color.TRANSPARENT -> ProfiTheme.buttonTransparent
            },
            contentColor = when (color) {
                Color.RED, Color.BLACK -> ProfiTheme.white
                Color.GREY, Color.TRANSPARENT -> ProfiTheme.textBlack
            }
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick
    ) {
        LBoldText(
            text = title,
            textColor = when (color) {
                Color.RED, Color.BLACK -> ProfiTheme.white
                Color.GREY, Color.TRANSPARENT -> ProfiTheme.textBlack
            }
        )
    }
}

enum class Color {
    RED,
    BLACK,
    GREY,
    TRANSPARENT
}