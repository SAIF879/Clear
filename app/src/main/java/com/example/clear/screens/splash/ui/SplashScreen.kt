package com.example.clear.screens.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.clear.R
import com.example.clear.screens.splash.components.splashNavigation
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.utils.commonComponents.ui.AnimatedLottie
import com.example.clear.utils.commonComponents.ui.ShowHeading


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


