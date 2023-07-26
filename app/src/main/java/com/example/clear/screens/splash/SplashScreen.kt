package com.example.clear.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.clear.R
import com.example.clear.navigation.NavGraphs
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.commonComponents.AnimatedLottie
import com.example.clear.utils.commonComponents.ShowHeading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController) {

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            ShowHeading(heading = stringResource(id = R.string.splash_heading), fontSize = 50)
            AnimatedLottie(R.raw.splash_animation)
        }

    }

    splashNavigation(coroutineScope = coroutineScope, navController = navController)

}


private fun splashNavigation(coroutineScope: CoroutineScope, navController: NavController) {
    coroutineScope.launch {
        delay(1500)
        navController.navigate(NavGraphs.Introduction)
    }
}