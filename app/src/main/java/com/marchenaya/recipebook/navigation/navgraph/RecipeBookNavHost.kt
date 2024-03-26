package com.marchenaya.recipebook.navigation.navgraph

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marchenaya.home.HomeScreen
import com.marchenaya.recipebook.navigation.destination.Filter
import com.marchenaya.recipebook.navigation.destination.Home
import com.marchenaya.recipebook.navigation.destination.Instructions
import com.marchenaya.recipebook.navigation.destination.RoutingRecipePrefix
import com.marchenaya.recipebook.navigation.destination.SearchDestinationRoute
import com.marchenaya.recipebook.navigation.destination.Settings
import com.marchenaya.recipebook.navigation.destination.Shopping
import com.marchenaya.settings.SettingsScreen
import com.marchenaya.shopping.ShoppingScreen

@Composable
fun RecipeBookNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = Home.route,
        enterTransition = {
            fadeIn()
        },
        exitTransition = {
            fadeOut()
        },
        modifier = modifier
    ) {
        composable(Home.route) {
            HomeScreen(
                onSearchClick = { navHostController.navigate(SearchDestinationRoute) },
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
        searchGraph(
            onFilterClick = {
                navHostController.navigate(Filter.route)
            }
        ) { recipeId ->
            navHostController.navigate("$RoutingRecipePrefix$recipeId")
        }
        recipeGraph(
            onInstructionsClick = {
                navHostController.navigate(Instructions.route)
            },
            onBackClick = {
                navHostController.popBackStack()
            }
        )
    }
}