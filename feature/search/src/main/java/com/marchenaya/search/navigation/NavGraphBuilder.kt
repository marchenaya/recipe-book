package com.marchenaya.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.marchenaya.search.FilterScreen
import com.marchenaya.search.SearchScreen

fun NavGraphBuilder.searchGraph(
    onBackClick: () -> Unit,
    onFilterClick: () -> Unit,
    onRecipeClick: () -> Unit
) {
    navigation(Search.route, searchRoute) {
        composable(Search.route) {
            SearchScreen(
                onBackClick = { onBackClick() },
                onFilterClick = { onFilterClick() },
                onRecipeClick = { onRecipeClick() }
            )
        }
        composable(Filter.route) {
            FilterScreen(onBackClick = { onBackClick() })
        }
    }
}