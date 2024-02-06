package com.marchenaya.recipebook.navigation.destination

import com.marchenaya.recipe.R

const val RecipeDestinationRoute = "recipe_route"

const val RoutingRecipePrefix = "recipe/"
const val RoutingInstructionsPrefix = "instructions/"

const val ArgKeyRecipeId = "recipeId"

sealed class RecipeDestination : RecipeBookDestination()

data object Recipe : RecipeDestination() {
    override val title: Int
        get() = R.string.recipe_title
    override val route: String
        get() = "$RoutingRecipePrefix{$ArgKeyRecipeId}"
}

data object Instructions : RecipeDestination() {
    override val title: Int
        get() = R.string.instructions_title
    override val route: String
        get() = "$RoutingInstructionsPrefix{$ArgKeyRecipeId}"
}