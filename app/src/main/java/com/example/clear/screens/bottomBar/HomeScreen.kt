package com.example.clear.screens.bottomBar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.clear.navigation.BottomNavGraph
import com.example.clear.ui.theme.DeepBlue
import com.example.clear.ui.theme.LightRed
import com.example.clear.ui.theme.TextWhite

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomBar(navController = navController)}) {
        BottomNavGraph(navController = navController)
    }

}
@Composable
fun BottomBar(navController: NavHostController){
val screens = listOf(
    BottomBarScreen.Notes,
    BottomBarScreen.Todos,
    BottomBarScreen.Memos
)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(containerColor = TextWhite) {
        screens.forEach{screen->
        AddItem(screen = screen, currentDestination = currentDestination , navController =navController )
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(label = { Text(text = screen.title) },
        icon = { Icon(imageVector = screen.icon, contentDescription = "bottom_icon") },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = { navController.navigate(screen.route){
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        } } ,
        )

}