package com.marchenaya.recipebook.navigation.destination

import androidx.annotation.StringRes

abstract class RecipeBookDestination {

    @get:StringRes
    abstract val title: Int

    abstract val route: String

    companion object {
        fun getByRoute(route: String): RecipeBookDestination? {
            return when (route) {
                Home.route -> Home
                Shopping.route -> Shopping
                Settings.route -> Settings
                Recipe.route -> Recipe
                Instructions.route -> Instructions
                Search.route -> Search
                Filter.route -> Filter
                else -> null
            }
        }

    }

}
