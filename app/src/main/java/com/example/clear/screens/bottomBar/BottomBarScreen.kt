package com.example.clear.screens.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.GraphicEq
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
     val iconFilled : ImageVector ,
     val iconOutlined :ImageVector,

){
    object Notes : BottomBarScreen(
        route =  "notes",
        title = "Notes",
        iconFilled = Icons.Filled.Description,
        iconOutlined = Icons.Outlined.Description
    )
    object Todos : BottomBarScreen(
        route = "todos",
        title = "To-do",
        iconFilled = Icons.Filled.Checklist,
        iconOutlined =Icons.Outlined.Checklist
    )
    object Dictionary : BottomBarScreen(
        route = "dictionary",
        title = "Dictionary",
       iconFilled =  Icons.Filled.MenuBook,
        iconOutlined = Icons.Outlined.MenuBook
    )
}