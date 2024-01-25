package com.marchenaya.recipebook.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marchenaya.home.HomeScreen
import com.marchenaya.recipebook.navigation.destination.Filter
import com.marchenaya.recipebook.navigation.destination.Home
import com.marchenaya.recipebook.navigation.destination.Instructions
import com.marchenaya.recipebook.navigation.destination.ROUTING_RECIPE_PREFIX
import com.marchenaya.recipebook.navigation.destination.SEARCH_DESTINATION_ROUTE
import com.marchenaya.recipebook.navigation.destination.Settings
import com.marchenaya.recipebook.navigation.destination.Shopping
import com.marchenaya.settings.SettingsScreen
import com.marchenaya.shopping.ShoppingScreen

@Composable
fun RecipeBookNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val onBackClick: () -> Unit = { navHostController.popBackStack() }
    NavHost(navController = navHostController, startDestination = Home.route, modifier = modifier) {
        composable(Home.route) {
            HomeScreen(
                title = Home.title,
                onSearchClick = { navHostController.navigate(SEARCH_DESTINATION_ROUTE) },
                onRecipeClick = { recipeId ->
                    navHostController.navigate("$ROUTING_RECIPE_PREFIX$recipeId")
                }
            )
        }
        composable(Shopping.route) {
            ShoppingScreen()
        }
        composable(Settings.route) {
            SettingsScreen()
        }
        searchGraph(
            onFilterClick = {
                navHostController.navigate(Filter.route)
            }
        ) { recipeId ->
            navHostController.navigate("$ROUTING_RECIPE_PREFIX$recipeId")
        }
        recipeGraph(
            onInstructionsClick = {
                navHostController.navigate(Instructions.route)
            }
        )
    }
}