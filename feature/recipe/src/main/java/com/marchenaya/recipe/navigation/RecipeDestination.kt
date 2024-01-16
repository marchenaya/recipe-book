package com.marchenaya.recipe.navigation

import androidx.annotation.StringRes
import com.marchenaya.recipe.R

const val RECIPE_ROUTE = "recipe_route"

const val ROUTING_RECIPE_PREFIX = "recipe/"
const val ROUTING_INSTRUCTIONS_PREFIX = "instructions/"

const val ARG_KEY_RECIPE_ID = "recipeId"

sealed class RecipeDestination(
    @StringRes val title: Int,
    val route: String
)

data object Recipe : RecipeDestination(
    title = R.string.recipe_title,
    route = "$ROUTING_RECIPE_PREFIX{$ARG_KEY_RECIPE_ID}",
)

data object Instructions : RecipeDestination(
    title = R.string.instructions_title,
    route = "$ROUTING_INSTRUCTIONS_PREFIX{$ARG_KEY_RECIPE_ID}",
)