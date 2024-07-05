package com.marchenaya.recipebook.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.marchenaya.recipebook.navigation.destination.SearchDestinationRoute
import com.marchenaya.recipebook.navigation.destination.Search
import com.marchenaya.search.SearchScreen

fun NavGraphBuilder.searchGraph(
    onRecipeClick: (Int) -> Unit
) {
    navigation(Search.route, SearchDestinationRoute) {
        composable(Search.route) {
            SearchScreen(
                onRecipeClick = { recipeId ->
                    onRecipeClick(recipeId)
                }
            )
        }
    }
}