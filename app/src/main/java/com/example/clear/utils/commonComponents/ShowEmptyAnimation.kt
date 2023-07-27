package com.example.clear.utils.commonComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.clear.utils.fonts.FontFamilyClear

@Composable
fun ShowEmptyAnimation(animatedRes: Int, text: String) {
    AnimatedLottie(animationRes = animatedRes)
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.White,
                fontFamily = FontFamilyClear.fontMedium,
                fontSize = 22.sp
            ) , textAlign = TextAlign.Center
        )
    }
}
