package ru.profi.skedda.android.composables.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 28.sp,
        fontWeight = FontWeight.W500,
        color = ProfiTheme.textBlack,
        textAlign = textAlign,
        lineHeight = 33.sp,
        letterSpacing = 0.2.sp
    )
}

@Composable
fun HeadlineText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 22.sp,
        fontWeight = FontWeight.W500,
        color = ProfiTheme.textBlack,
        textAlign = textAlign,
        lineHeight = 26.sp,
        letterSpacing = 0.2.sp
    )
}

@Composable
fun LBoldText(
    text: String,
    textColor: Color = ProfiTheme.textBlack,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        fontSize = 17.sp,
        textAlign = textAlign,
//        fontFamily = FontFamily(Typeface.create()),
        fontWeight = FontWeight.W500,
        color = textColor,
        lineHeight = 22.sp,
        letterSpacing = 0.2.sp
    )
}

@Composable
fun LRegularText(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = ProfiTheme.textBlack
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 17.sp,
        fontWeight = FontWeight.W400,
        color = textColor,
        lineHeight = 22.sp,
        letterSpacing = 0.2.sp
    )
}
