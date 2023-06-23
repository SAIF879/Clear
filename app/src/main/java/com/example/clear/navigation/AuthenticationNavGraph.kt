package com.example.clear.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.clear.screens.authentication.ui.EnterDetailsScreen
import com.example.clear.screens.authentication.ui.LoginScreen


fun NavGraphBuilder.authenticationNavGraph(navController: NavController){
    navigation(route = NavGraphs.Authentication , startDestination =  AuthenticationScreens.LoginScreen.route){
        composable(route = AuthenticationScreens.LoginScreen.route){
            LoginScreen(navController = navController)
        }
        composable(route = AuthenticationScreens.EnterDetailsScreen.route){
            EnterDetailsScreen(navController = navController)
        }
    }
}
sealed class AuthenticationScreens(val route : String){
    object LoginScreen : AuthenticationScreens(route = "login")
    object EnterDetailsScreen : AuthenticationScreens(route = "enter_details")
}