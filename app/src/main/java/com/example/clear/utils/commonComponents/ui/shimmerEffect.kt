package com.example.clear.utils.commonComponents.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(

                Color.White.copy(0.9f),

                Color.White.copy(0.2f),

                Color.White.copy(0.9f)

            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}

@Composable
fun ShimmerAnimation() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp), verticalArrangement = Arrangement.Center
    ) {
        Spacer(
            modifier = Modifier
                .size(100.dp, 50.dp)
                .padding(10.dp)
                .shimmerEffect()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(10.dp)
                .shimmerEffect()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(10.dp)
                .shimmerEffect()
        )
        Spacer(
            modifier = Modifier
                .size(100.dp, 50.dp)
                .padding(10.dp)
                .shimmerEffect()
        )
        Divider(modifier = Modifier.shimmerEffect())
    }
}
