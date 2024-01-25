package com.marchenaya.recipebook.navigation.destination

const val SEARCH_DESTINATION_ROUTE = "search_route"

const val SEARCH_ROUTE = "search"
const val FILTER_ROUTE = "filter"

sealed class SearchDestination : RecipeBookDestination()

data object Search : SearchDestination() {
    override val title: Int
        get() = com.marchenaya.search.R.string.search_title
    override val route: String
        get() = SEARCH_ROUTE

}

data object Filter : SearchDestination() {
    override val title: Int
        get() = com.marchenaya.search.R.string.filter_title
    override val route: String
        get() = FILTER_ROUTE

}