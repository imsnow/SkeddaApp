package ru.profi.skedda.android.composables.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun DsTextField(
    modifier: Modifier = Modifier,
    value: String = "Hello",
    hint: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit = {}
) {
    TextField(
        modifier = modifier.height(50.dp),
        value = value,
        textStyle = TextStyle(
            fontSize = 17.sp
        ),
        visualTransformation = visualTransformation,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = ProfiTheme.buttonGrey,
            textColor = ProfiTheme.textBlack
        )
    )
}
