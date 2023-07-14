package com.example.clear.screens.home.dictionary.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.example.clear.ui.theme.RedOrange
import com.example.clear.utils.fonts.FontFamilyClear

//header
//caution
//animation
//button




@Composable
fun GetCaution(caution: String = "" , modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.Start , modifier = modifier.fillMaxWidth()) {
        Text(
            text = caution,
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamilyClear.fontMedium,
                color = Color.White
            ),modifier = Modifier.padding(0.dp)
        )
    }
}

@Composable
fun MemoHeader(onClick : ()-> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.List,
            contentDescription = "options_icon",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clickable { onClick.invoke() }
        )
    }
}

@Composable
fun RecordAnimation(composition: LottieComposition? , count : MutableState<Int>){

    LottieAnimation(composition = composition, modifier = Modifier.size(350.dp), isPlaying =count.value!=0)

}

@Composable
fun RecordButton(start: String, stop: String, click: MutableState<Int>, onClick: () -> Unit) {

    Button(
        onClick = {
            onClick.invoke()
            if (click.value == 0) {
                click.value = 1
            } else if (click.value == 1) {
                click.value = 0
            }
        }, colors = ButtonDefaults.buttonColors(containerColor = RedOrange)
    ) {
        Text(
            text = if (click.value == 0) start else stop,
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamilyClear.fontMedium,
                color = Color.White
            )
        )
    }
}