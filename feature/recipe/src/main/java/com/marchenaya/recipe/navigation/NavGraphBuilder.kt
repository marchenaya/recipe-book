package com.marchenaya.recipe.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.marchenaya.recipe.InstructionsScreen
import com.marchenaya.recipe.RecipeScreen

fun NavGraphBuilder.recipeGraph(onBackClick: () -> Unit, onInstructionsClick: () -> Unit) {
    navigation(Recipe.baseRoute, RECIPE_ROUTE) {
        composable(
            route = Recipe.baseRoute,
            arguments = Recipe.arguments
        ) { navBackStackEntry ->
            RecipeScreen(
                recipeId = navBackStackEntry.arguments?.getInt(ARG_KEY_RECIPE_ID),
                onBackClick = { onBackClick() },
                onInstructionsClick = { onInstructionsClick() })
        }
        composable(Instructions.baseRoute) {
            InstructionsScreen(onBackClick = { onBackClick() })
        }
    }
}