package com.marchenaya.recipebook.navigation.destination

import com.marchenaya.recipe.R

const val RECIPE_DESTINATION_ROUTE = "recipe_route"

const val ROUTING_RECIPE_PREFIX = "recipe/"
const val ROUTING_INSTRUCTIONS_PREFIX = "instructions/"

const val ARG_KEY_RECIPE_ID = "recipeId"

sealed class RecipeDestination : RecipeBookDestination()

data object Recipe : RecipeDestination() {
    override val title: Int
        get() = R.string.recipe_title
    override val route: String
        get() = "$ROUTING_RECIPE_PREFIX{$ARG_KEY_RECIPE_ID}"
}

data object Instructions : RecipeDestination() {
    override val title: Int
        get() = R.string.instructions_title
    override val route: String
        get() = "$ROUTING_INSTRUCTIONS_PREFIX{$ARG_KEY_RECIPE_ID}"
}