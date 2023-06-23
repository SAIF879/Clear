package com.example.clear.utils.commonComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clear.R
import com.example.clear.ui.theme.ButtonBlue
import com.example.clear.ui.theme.TextWhite
import com.example.clear.utils.fonts.FontFamilyClear


@Composable
fun GenerateButtonWithIcon(
    text: String ,
    icon : Int,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    horizontalPadding: Int = 10,
    verticalPadding: Int = 10,
    height: Int = 50,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier
            .padding(horizontalPadding.dp, verticalPadding.dp)
            .height(height.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(ButtonBlue),
        enabled = isEnabled,
    ) {
       Image(painter = painterResource(icon), contentDescription = "button_icon" )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamilyClear.fontRegular,
                color = if (isEnabled) TextWhite else Color.Black
            ),
            modifier = modifier.align(Alignment.CenterVertically)
        )
    }
}
