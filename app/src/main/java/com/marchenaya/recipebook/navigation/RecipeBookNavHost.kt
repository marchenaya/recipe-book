package com.marchenaya.recipebook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marchenaya.home.HomeScreen
import com.marchenaya.recipe.navigation.Instructions
import com.marchenaya.recipe.navigation.ROUTING_RECIPE_PREFIX
import com.marchenaya.recipe.navigation.Recipe
import com.marchenaya.recipe.navigation.recipeGraph
import com.marchenaya.search.navigation.Filter
import com.marchenaya.search.navigation.searchGraph
import com.marchenaya.search.navigation.searchRoute
import com.marchenaya.settings.SettingsScreen
import com.marchenaya.shopping.ShoppingScreen

@Composable
fun RecipeBookNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val onBackClick: () -> Unit = { navHostController.popBackStack() }
    NavHost(navController = navHostController, startDestination = Home.route, modifier = modifier) {
        composable(Home.route) {
            HomeScreen(
                title = Home.title,
                onSearchClick = { navHostController.navigate(searchRoute) },
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
        searchGraph(onBackClick = { onBackClick() },
            onFilterClick = {
                navHostController.navigate(Filter.route)
            }, onRecipeClick = {
                navHostController.navigate(Recipe.route)
            }
        )
        recipeGraph(onBackClick = { onBackClick() },
            onInstructionsClick = {
                navHostController.navigate(Instructions.route)
            })
    }
}