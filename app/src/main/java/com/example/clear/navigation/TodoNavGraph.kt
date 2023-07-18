package com.example.clear.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.clear.screens.home.todo.ui.CompletedTodoScreen



fun NavGraphBuilder.todoNavGraph(navController: NavController){
    navigation(route = NavGraphs.Todo , startDestination = TodoScreens.CompletedTodoScreen.route){
        composable(route = TodoScreens.CompletedTodoScreen.route){
            CompletedTodoScreen(navController = navController)
        }
    }
}
sealed class TodoScreens(val route : String){
    object CompletedTodoScreen : TodoScreens(route = "completed_todo")
}