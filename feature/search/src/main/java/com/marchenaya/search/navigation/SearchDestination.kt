package com.marchenaya.search.navigation

import androidx.annotation.StringRes

const val searchRoute = "search_route"

sealed class SearchDestination(
    @StringRes val title: Int,
    val route: String
)

object Search : SearchDestination(
    title = com.marchenaya.search.R.string.search_title,
    route = "search"
)

object Filter : SearchDestination(
    title = com.marchenaya.search.R.string.filter_title,
    route = "filter"
)