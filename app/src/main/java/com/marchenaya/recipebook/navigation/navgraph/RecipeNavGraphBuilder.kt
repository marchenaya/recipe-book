package com.marchenaya.recipebook.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.marchenaya.recipe.InstructionsScreen
import com.marchenaya.recipe.RecipeScreen
import com.marchenaya.recipebook.navigation.destination.ARG_KEY_RECIPE_ID
import com.marchenaya.recipebook.navigation.destination.Instructions
import com.marchenaya.recipebook.navigation.destination.RECIPE_DESTINATION_ROUTE
import com.marchenaya.recipebook.navigation.destination.Recipe

fun NavGraphBuilder.recipeGraph(onInstructionsClick: () -> Unit) {
    navigation(Recipe.route, RECIPE_DESTINATION_ROUTE) {
        composable(
            route = Recipe.route,
            arguments = listOf(
                navArgument(ARG_KEY_RECIPE_ID) { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            RecipeScreen(
                recipeId = navBackStackEntry.arguments?.getInt(ARG_KEY_RECIPE_ID),
                onInstructionsClick = { onInstructionsClick() })
        }
        composable(Instructions.route) {
            InstructionsScreen()
        }
    }
}