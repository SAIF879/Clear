package com.example.clear.screens.splash.components

import androidx.navigation.NavController
import com.example.clear.navigation.NavGraphs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

 fun splashNavigation(coroutineScope: CoroutineScope, navController: NavController) {
    coroutineScope.launch {
        delay(1500)
        navController.navigate(NavGraphs.Introduction)
    }
}