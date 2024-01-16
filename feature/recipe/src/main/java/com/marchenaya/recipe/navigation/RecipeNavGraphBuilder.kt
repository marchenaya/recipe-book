package com.marchenaya.recipe.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.marchenaya.recipe.InstructionsScreen
import com.marchenaya.recipe.RecipeScreen

fun NavGraphBuilder.recipeGraph(onBackClick: () -> Unit, onInstructionsClick: () -> Unit) {
    navigation(Recipe.route, RECIPE_ROUTE) {
        composable(
            route = Recipe.route,
            arguments = listOf(
                navArgument(ARG_KEY_RECIPE_ID) { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            RecipeScreen(
                recipeId = navBackStackEntry.arguments?.getInt(ARG_KEY_RECIPE_ID),
                onBackClick = { onBackClick() },
                onInstructionsClick = { onInstructionsClick() })
        }
        composable(Instructions.route) {
            InstructionsScreen(onBackClick = { onBackClick() })
        }
    }
}