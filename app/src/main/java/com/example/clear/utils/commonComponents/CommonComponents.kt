package com.example.clear.utils.commonComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.clear.ui.theme.LightRed

@Composable
fun CircularButton(modifier: Modifier = Modifier,icon : ImageVector, size : Int = 50,   onClick: () -> Unit = {}) {
    Box(
        modifier = modifier
            .size(size.dp)
            .background(LightRed, shape = CircleShape)
            .aspectRatio(1f)
            .clickable { onClick.invoke() },
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