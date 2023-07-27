package com.example.clear.utils.commonComponents.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun GenerateButton(
    text: String = "Continue",
    horizontalPadding: Int = 10,
    verticalPadding: Int = 10,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    height: Int = 65,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = Modifier
            .height(height.dp)
            .fillMaxWidth()
            .padding(horizontalPadding.dp, verticalPadding.dp),
        enabled = isEnabled, shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = White),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        )
    )
    {
        Text(
            text = text, style = TextStyle(
                color = if (isEnabled) Black else White,
                fontSize = 18.sp,
            ), modifier = Modifier.align(Alignment.CenterVertically)
        )
    }

}