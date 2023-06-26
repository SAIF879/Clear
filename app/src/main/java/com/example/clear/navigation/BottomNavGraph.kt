package com.example.clear.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clear.room.model.NoteData
import com.example.clear.screens.bottomBar.BottomBarScreen
import com.example.clear.screens.home.memo.ui.VoiceMemoScreen
import com.example.clear.screens.home.note.ui.NoteScreen
import com.example.clear.screens.home.todo.ui.TodoScreen


@Composable
fun BottomNavGraph(
    navController: NavHostController,
){
    NavHost(navController = navController, startDestination = BottomBarScreen.Notes.route ){

        composable(route = BottomBarScreen.Notes.route){ NoteScreen( navController = navController)}
        composable(route = BottomBarScreen.Todos.route){ TodoScreen()}
        composable(route = BottomBarScreen.Memos.route){ VoiceMemoScreen() }
        noteNavGraph(navController = navController)

    }

}