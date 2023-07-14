package com.example.clear.screens.bottomBar

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.clear.navigation.BottomNavGraph
import com.example.clear.ui.theme.LightRed
import com.example.clear.utils.fonts.FontFamilyClear

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val navController = rememberNavController()
    val showBottomBar = rememberSaveable {
        mutableStateOf(true)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when(navBackStackEntry?.destination?.route){
        "notes" -> showBottomBar.value = true
        "todos" ->showBottomBar.value = true
        "dictionary" -> showBottomBar.value = true
        else -> showBottomBar.value = false
    }

    Scaffold(bottomBar = { BottomBar(navController = navController , bottomBarState = showBottomBar)}) {
        BottomNavGraph(navController = navController)
    }


}

@Composable
fun BottomBar(navController: NavHostController, bottomBarState: MutableState<Boolean>){
val screens = listOf(
    BottomBarScreen.Notes,
    BottomBarScreen.Todos,
    BottomBarScreen.Dictionary
)


    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = {it}),
        content = {
            BottomBarScreenContent(navController = navController , bottomBarState  = bottomBarState, screens = screens)
        }
    )
}

private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f,0.0f,0.0f,0.0f)
}

@Composable
fun BottomBarScreenContent(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    screens: List<BottomBarScreen>
) {
    CompositionLocalProvider() {
        NavigationBar(containerColor = LightRed) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            screens.forEach { menuItem ->
                val selected =
                    currentDestination?.hierarchy?.any { it.route == menuItem.route } == true

                // adding each item
                NavigationBarItem(
                    selected = (selected),
                    onClick = {
                        if (selected) return@NavigationBarItem
                        navController.navigate(menuItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (selected) menuItem.iconFilled else menuItem.iconOutlined,
                            contentDescription = menuItem.route
                        )
                    },
                    label = {
                        Text(
                            text = menuItem.title,
                            style = TextStyle(
                                fontFamily = if (selected) FontFamilyClear.fontMedium else FontFamilyClear.fontRegular,
                                fontSize = 14.sp
                            )
                        )
                    },
                    enabled = true
                )
            }
        }
    }
}




