package com.marchenaya.recipebook.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.marchenaya.recipe.InstructionsScreen
import com.marchenaya.recipe.recipe.RecipeScreen
import com.marchenaya.recipebook.navigation.destination.ArgKeyRecipeId
import com.marchenaya.recipebook.navigation.destination.Instructions
import com.marchenaya.recipebook.navigation.destination.Recipe
import com.marchenaya.recipebook.navigation.destination.RecipeDestinationRoute

fun NavGraphBuilder.recipeGraph(onInstructionsClick: () -> Unit, onBackClick: () -> Unit) {
    navigation(Recipe.route, RecipeDestinationRoute) {
        composable(
            route = Recipe.route,
            arguments = listOf(
                navArgument(ArgKeyRecipeId) { type = NavType.IntType },
            )
        ) { navBackStackEntry ->
            RecipeScreen(
                recipeId = navBackStackEntry.arguments?.getInt(ArgKeyRecipeId),
                onBackClick = { onBackClick() }
            )
        }
        composable(Instructions.route) {
            InstructionsScreen()
        }
    }
}