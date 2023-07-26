package com.example.clear.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.clear.screens.introduction.IntroNotesScreen
import com.example.clear.screens.introduction.IntroTodoScreen

fun NavGraphBuilder.introductionNavGraph(navController: NavController) {
    navigation(
        route = NavGraphs.Introduction,
        startDestination = IntroScreens.IntroNotesScreen.route
    ) {
        composable(route = IntroScreens.IntroNotesScreen.route) {
            IntroNotesScreen(navController = navController)
        }
        composable(route = IntroScreens.IntroTodoScreen.route) {
            IntroTodoScreen(navController = navController)
        }
    }
}

sealed class IntroScreens(val route: String) {
    object IntroNotesScreen : IntroScreens(route = "intro_notes")
    object IntroTodoScreen : IntroScreens(route = "intro_todo")
}
