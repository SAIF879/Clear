package com.example.clear.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.clear.screens.splash.ui.SplashScreen

fun NavGraphBuilder.splashNavGraph(navController: NavController) {
    navigation(route = NavGraphs.Splash, startDestination = SplashScreens.SplashScreen.route) {
        composable(route = SplashScreens.SplashScreen.route) { SplashScreen(navController = navController) }
    }
}

sealed class SplashScreens(val route: String) {
    object SplashScreen : SplashScreens(route = "splash_screen")
}

