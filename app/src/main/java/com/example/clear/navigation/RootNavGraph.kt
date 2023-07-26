package com.example.clear.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clear.screens.bottomBar.HomeScreen

@Composable
fun RootNavGraph(navController: NavHostController){
    NavHost(navController = navController, route = NavGraphs.Root , startDestination = NavGraphs.Splash  ){
        splashNavGraph(navController)
        introductionNavGraph(navController)
        authenticationNavGraph(navController)
        composable(route =NavGraphs.Bottom){
            HomeScreen()
        }
    }
}

object NavGraphs{
    const val Root = "root"
    const val Introduction = "introduction"
    const val Authentication = "authentication"
    const val Bottom = "bottom"
    const val Note = "note"
    const val Todo = "todo"
    const val Dictionary = "dictionary_screen"
    const val Splash = "splash"
}