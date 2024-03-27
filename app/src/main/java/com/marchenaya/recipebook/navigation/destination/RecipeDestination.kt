package com.marchenaya.recipebook.navigation.destination

import com.marchenaya.recipe.R

const val RecipeDestinationRoute = "recipe_route"

const val RoutingRecipePrefix = "recipe/"

const val ArgKeyRecipeId = "recipeId"

sealed class RecipeDestination : RecipeBookDestination()

data object Recipe : RecipeDestination() {
    override val title: Int
        get() = R.string.recipe_title
    override val route: String
        get() = "$RoutingRecipePrefix{$ArgKeyRecipeId}"
}