package com.marchenaya.recipebook.navigation.destination

const val SearchDestinationRoute = "search_route"

const val SearchRoute = "search"
const val FilterRoute = "filter"

sealed class SearchDestination : RecipeBookDestination()

data object Search : SearchDestination() {
    override val title: Int
        get() = com.marchenaya.search.R.string.search_title
    override val route: String
        get() = SearchRoute

}

data object Filter : SearchDestination() {
    override val title: Int
        get() = com.marchenaya.search.R.string.filter_title
    override val route: String
        get() = FilterRoute

}