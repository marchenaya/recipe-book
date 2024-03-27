package com.marchenaya.recipebook.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marchenaya.home.HomeScreen
import com.marchenaya.recipebook.navigation.destination.Home
import com.marchenaya.recipebook.navigation.destination.RoutingRecipePrefix
import com.marchenaya.recipebook.navigation.destination.Settings
import com.marchenaya.recipebook.navigation.destination.Shopping
import com.marchenaya.settings.SettingsScreen
import com.marchenaya.shopping.ShoppingScreen

@Composable
fun RecipeBookNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(Home.route) {
            HomeScreen(
                onRecipeClick = { recipeId ->
                    navHostController.navigate("$RoutingRecipePrefix$recipeId")
                }
            )
        }
        composable(Shopping.route) {
            ShoppingScreen()
        }
        composable(Settings.route) {
            SettingsScreen()
        }
        recipeGraph {
            navHostController.popBackStack()
        }
    }
}