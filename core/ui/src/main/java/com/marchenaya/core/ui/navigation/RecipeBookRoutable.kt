package com.marchenaya.core.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions

interface RecipeBookRoutable {

    val route: String

    companion object {
        fun NavController.navigate(
            routable: RecipeBookRoutable,
            navOptions: NavOptions? = null,
        ) {
            this.navigate(route = routable.route, navOptions = navOptions)
        }

        fun NavController.navigate(
            routable: RecipeBookRoutable,
            builder: NavOptionsBuilder.() -> Unit,
        ) {
            this.navigate(routable, navOptions(builder))
        }
    }
}
