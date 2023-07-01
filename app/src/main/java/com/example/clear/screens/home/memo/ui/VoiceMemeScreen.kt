package com.example.clear.screens.home.memo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.clear.R
import com.example.clear.screens.home.memo.components.GetCaution
import com.example.clear.screens.home.memo.components.MemoHeader
import com.example.clear.screens.home.memo.components.RecordAnimation
import com.example.clear.screens.home.memo.components.RecordButton
import com.example.clear.ui.theme.DeepBlue

@Composable
fun VoiceMemoScreen(){
    val clicks = remember{
        mutableStateOf(0)
    }
    val splashAnimation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.reocrd))
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(DeepBlue)){
        Column( horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            MemoHeader{}
            GetCaution(caution = stringResource(id = R.string.caution) , modifier = Modifier.weight(0.5f))
            Spacer(modifier = Modifier.size(20.dp))
           Column(verticalArrangement = Arrangement.SpaceBetween , horizontalAlignment = Alignment.CenterHorizontally , modifier = Modifier.weight(2f) ) {
               RecordAnimation(composition = splashAnimation , count = clicks)
               RecordButton(start = "start", stop ="stop" , click =clicks ) {}
           }
            Spacer(modifier = Modifier.size(100.dp))

        }
    }
}