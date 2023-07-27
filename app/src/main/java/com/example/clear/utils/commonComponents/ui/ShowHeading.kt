package com.example.clear.utils.commonComponents.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.fonts.FontFamilyClear

@Composable
fun ShowHeading(heading: String, fontSize: Int) {
    Text(
        text = heading,
        style = TextStyle(
            fontSize = fontSize.sp,
            color = TextWhite,
            textAlign = TextAlign.Center,
            fontFamily = FontFamilyClear.fontMedium
        )
    )
}