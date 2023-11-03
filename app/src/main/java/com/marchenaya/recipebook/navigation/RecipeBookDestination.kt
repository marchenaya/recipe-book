package com.marchenaya.recipebook.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class RecipeBookDestination(
    @StringRes val title: Int,
    val selectedIcon: ImageVector,
    val route: String
) {
    companion object {
        val bottomBarScreens = listOf(Home, Shopping, Settings)
    }

}

object Home : RecipeBookDestination(
    title = com.marchenaya.home.R.string.home_title,
    selectedIcon = Icons.Rounded.Home,
    route = "home"
)

object Shopping : RecipeBookDestination(
    title = com.marchenaya.shopping.R.string.shopping_title,
    selectedIcon = Icons.Rounded.Checklist,
    route = "shopping"
)

object Settings : RecipeBookDestination(
    title = com.marchenaya.settings.R.string.settings_title,
    selectedIcon = Icons.Rounded.Settings,
    route = "settings"
)
