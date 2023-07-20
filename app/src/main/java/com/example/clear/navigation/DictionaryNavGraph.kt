package com.example.clear.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.clear.screens.home.dictionary.ui.SearchWordScreen
import com.example.clear.screens.home.dictionary.util.DictionaryViewModel

fun NavGraphBuilder.dictionaryNavGraph(navController: NavController, viewModel: DictionaryViewModel) {
    navigation(route=NavGraphs.Dictionary , startDestination = DictionaryScreens.SearchWordScreen.route){
        composable(route = DictionaryScreens.SearchWordScreen.route){ SearchWordScreen(navController = navController,viewModel=viewModel)}
    }
}

sealed class DictionaryScreens(val route : String){
    object SearchWordScreen : DictionaryScreens(route = "search_word")
}

