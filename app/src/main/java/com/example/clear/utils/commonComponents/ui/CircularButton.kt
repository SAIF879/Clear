package com.example.clear.utils.commonComponents.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.clear.ui.theme.LightRed
import com.example.clear.utils.commonComponents.util.bounceClick

@Composable
fun CircularButton(modifier: Modifier = Modifier,icon : ImageVector, size : Int = 50,   onClick: () -> Unit = {}) {
    Box(
        modifier = modifier.bounceClick()
            .size(size.dp)
            .background(LightRed, shape = CircleShape)
            .aspectRatio(1f)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = " icon",
            tint = Color.White,
            modifier = Modifier.fillMaxSize()
        )
    }
}
