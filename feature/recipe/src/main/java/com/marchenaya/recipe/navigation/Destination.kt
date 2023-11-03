package com.marchenaya.recipe.navigation

import androidx.annotation.StringRes
import com.marchenaya.recipe.R

const val recipeRoute = "recipe_route"

sealed class RecipeDestination(
    @StringRes val title: Int,
    val route: String
)

object Recipe : RecipeDestination(
    title = R.string.recipe_title,
    route = "recipe/{recipeId}"
)

object Instructions : RecipeDestination(
    title = R.string.instructions_title,
    route = "instructions/{recipeId}"
)