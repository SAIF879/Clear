package com.example.clear.screens.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SurroundSound
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Notes : BottomBarScreen(
        route =  "notes",
        title = "Notes",
        icon = Icons.Default.Home
    )
    object Todos : BottomBarScreen(
        route = "todos",
        title = "To-do",
        icon = Icons.Default.Done
    )
    object Memos : BottomBarScreen(
        route = "memos",
        title = "Voice-Memo",
        icon = Icons.Default.SurroundSound
    )
}