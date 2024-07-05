package com.marchenaya.recipebook.navigation.destination

const val SearchDestinationRoute = "search_route"

const val SearchRoute = "search"

sealed class SearchDestination : RecipeBookDestination()

data object Search : SearchDestination() {
    override val title: Int
        get() = com.marchenaya.search.R.string.search_title
    override val route: String
        get() = SearchRoute
}