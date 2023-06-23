package com.example.clear.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(navController: NavHostController){
    NavHost(navController = navController, route = NavGraphs.Root , startDestination = NavGraphs.Introduction ){
        introductionNavGraph(navController)
    }
}

object NavGraphs{
    const val Root = "root"
    const val Introduction = "introduction"
    const val Authentication = "authentication"
    const val Bottom = "bottom"
    const val Home = "home"
    const val Splash = "splash"
}