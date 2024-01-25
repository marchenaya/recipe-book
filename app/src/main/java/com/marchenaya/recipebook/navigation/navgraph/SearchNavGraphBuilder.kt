package com.marchenaya.recipebook.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.marchenaya.recipebook.navigation.destination.Filter
import com.marchenaya.recipebook.navigation.destination.SEARCH_DESTINATION_ROUTE
import com.marchenaya.recipebook.navigation.destination.Search
import com.marchenaya.search.FilterScreen
import com.marchenaya.search.SearchScreen

fun NavGraphBuilder.searchGraph(
    onFilterClick: () -> Unit,
    onRecipeClick: (Int) -> Unit
) {
    navigation(Search.route, SEARCH_DESTINATION_ROUTE) {
        composable(Search.route) {
            SearchScreen(
                onFilterClick = { onFilterClick() },
                onRecipeClick = { recipeId ->
                    onRecipeClick(recipeId)
                }
            )
        }
        composable(Filter.route) {
            FilterScreen()
        }
    }
}