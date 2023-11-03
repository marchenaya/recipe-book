package com.marchenaya.recipe.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.marchenaya.recipe.InstructionsScreen
import com.marchenaya.recipe.RecipeScreen

fun NavGraphBuilder.recipeGraph(onBackClick: () -> Unit, onInstructionsClick: () -> Unit) {
    navigation(Recipe.route, recipeRoute) {
        composable(Recipe.route) {
            RecipeScreen(
                onBackClick = { onBackClick() },
                onInstructionsClick = { onInstructionsClick() })
        }
        composable(Instructions.route) {
            InstructionsScreen(onBackClick = { onBackClick() })
        }
    }
}