package com.example.clear.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.clear.screens.home.note.ui.CreateNotesScreen
import com.example.clear.screens.home.note.ui.EditNotesScreen
import com.example.clear.screens.home.note.util.NoteViewModel

fun NavGraphBuilder.noteNavGraph(navController: NavController, viewModel: NoteViewModel) {
    navigation(route = NavGraphs.Note, startDestination = NoteScreens.CreateNotesScreen.route) {
        composable(route = NoteScreens.CreateNotesScreen.route) {
            CreateNotesScreen(
                navController = navController,
                noteViewModel = viewModel
            )
        }
        composable(route = NoteScreens.EditNotesScreen.route) {
            EditNotesScreen(
                navController = navController,
                noteViewModel = viewModel
            )
        }
    }
}

sealed class NoteScreens(val route: String) {
    object CreateNotesScreen : NoteScreens(route = "create_note")
    object EditNotesScreen : NoteScreens(route = "edit_note")
}