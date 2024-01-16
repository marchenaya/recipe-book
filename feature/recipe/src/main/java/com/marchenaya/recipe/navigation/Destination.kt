package com.marchenaya.recipe.navigation

import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.marchenaya.recipe.R

const val RECIPE_ROUTE = "recipe_route"

const val ROUTING_RECIPE_PREFIX = "recipe/"
const val ROUTING_INSTRUCTIONS_PREFIX = "instructions/"

const val ARG_KEY_RECIPE_ID = "recipeId"

sealed class RecipeDestination(
    @StringRes val title: Int,
    val arguments: List<NamedNavArgument>
) {
    abstract val baseRoute: String
}


class Recipe(
    val recipeId: Int
) : com.marchenaya.core.ui.navigation.RecipeBookRoutable {
    override val route: String
        get() = "$ROUTING_RECIPE_PREFIX$recipeId"

    companion object : RecipeDestination(
        title = R.string.recipe_title,
        arguments = listOf(
            navArgument(ARG_KEY_RECIPE_ID) { type = NavType.IntType }
        )
    ) {

        override val baseRoute: String
            get() = "$ROUTING_RECIPE_PREFIX{$ARG_KEY_RECIPE_ID}"
    }
}

class Instructions(
    val recipeId: Int
) : com.marchenaya.core.ui.navigation.RecipeBookRoutable {
    override val route: String
        get() = "$ROUTING_INSTRUCTIONS_PREFIX$recipeId"

    companion object : RecipeDestination(
        title = R.string.recipe_title,
        arguments = listOf(
            navArgument(ARG_KEY_RECIPE_ID) { type = NavType.IntType }
        )
    ) {

        override val baseRoute: String
            get() = "$ROUTING_INSTRUCTIONS_PREFIX{$ARG_KEY_RECIPE_ID}"
    }
}