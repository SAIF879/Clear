package com.example.clear.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clear.screens.bottomBar.BottomBarScreen
import com.example.clear.screens.home.dictionary.ui.DictionaryScreen
import com.example.clear.screens.home.dictionary.util.DictionaryViewModel
import com.example.clear.screens.home.note.ui.NoteScreen
import com.example.clear.screens.home.note.util.NoteViewModel
import com.example.clear.screens.home.todo.ui.TodoScreen


@Composable
fun BottomNavGraph(
    navController: NavHostController,
) {
    val viewModel: DictionaryViewModel = hiltViewModel()
    val noteViewModel: NoteViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = BottomBarScreen.Notes.route) {
        composable(route = BottomBarScreen.Notes.route) {
            NoteScreen(
                navController = navController,
                noteViewModel = noteViewModel
            )
        }
        composable(route = BottomBarScreen.Todos.route) { TodoScreen(navController = navController) }
        composable(route = BottomBarScreen.Dictionary.route) {
            DictionaryScreen(
                navController = navController,
                dictionaryViewModel = viewModel
            )
        }
        noteNavGraph(navController = navController, viewModel = noteViewModel)

        dictionaryNavGraph(navController = navController, viewModel = viewModel)

        todoNavGraph(navController = navController)

    }

}