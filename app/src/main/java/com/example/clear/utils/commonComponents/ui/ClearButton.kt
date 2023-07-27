package com.example.clear.utils.commonComponents.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clear.ui.theme.RedOrange
import com.example.clear.utils.fonts.FontFamilyClear

@Composable
fun ClearButton(text: String, onClick: () -> Unit) {
    Button(onClick = { onClick.invoke() },
        colors = ButtonDefaults.buttonColors(containerColor = RedOrange),
        shape = RoundedCornerShape(0.dp) , modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(10.dp)) {
        Text(
            text = text,
            style = TextStyle(fontFamily = FontFamilyClear.fontBold, fontSize = 25.sp)
        )
    }
}