package com.example.clear.utils.commonComponents

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
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
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp) , verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier
            .size(100.dp, 50.dp)
            .padding(10.dp)
            .shimmerEffect())
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(10.dp)
            .shimmerEffect() )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(10.dp)
            .shimmerEffect() )
        Spacer(modifier = Modifier
            .size(100.dp, 50.dp)
            .padding(10.dp)
            .shimmerEffect())
        Divider(modifier = Modifier.shimmerEffect())
    }
}
//
//Box(modifier = Modifier
//.fillMaxWidth()
//.height(100.dp)
//.padding(10.dp)){
//    Row(verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceAround) {
////           Image(painter = painterResource(id = R.drawable.truck), contentDescription = "vehicle_image", modifier = Modifier.size(70.dp))
//        Row(verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.Start , modifier = Modifier.weight(1f)){
//            Spacer(modifier = Modifier
//                .size(70.dp).clip(RoundedCornerShape(5.dp))
//                .shimmerEffect())
//            Spacer(modifier = Modifier.size(20.dp))
//            Column(verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.Start , modifier = Modifier.padding(5.dp))  {
//                Spacer(modifier = Modifier.clip(RoundedCornerShape(2.dp))
//                    .size(25.dp)
//                    .shimmerEffect())
//                Spacer(modifier = Modifier.size(5.dp))
//                Row {
//                    Spacer(modifier = Modifier.clip(RoundedCornerShape(2.dp))
//                        .size(20.dp)
//                        .shimmerEffect())
//                    Spacer(modifier = Modifier.clip(RoundedCornerShape(2.dp))
//                        .size(20.dp)
//                        .shimmerEffect())
//                    Spacer(modifier = Modifier.clip(RoundedCornerShape(2.dp))
//                        .size(20.dp)
//                        .shimmerEffect())
//                }
//                Spacer(modifier = Modifier.size(5.dp))
//                Row (horizontalArrangement = Arrangement.Center , verticalAlignment = Alignment.CenterVertically){
//                    Spacer(modifier = Modifier
//                        .size(20.dp).clip(RoundedCornerShape(2.dp))
//                        .shimmerEffect())
//                    Spacer(modifier = Modifier.size(10.dp))
//                    Spacer(modifier = Modifier
//                        .height(25.dp)
//                        .shimmerEffect().clip(RoundedCornerShape(4.dp))
//                    )
//                }
//                Spacer(modifier = Modifier.size(5.dp))
//            }
//        }
//        Spacer(modifier = Modifier.width(100.dp)
//            .height(30.dp).shimmerEffect().clip(RoundedCornerShape(10.dp)))
//
//    }
//}
