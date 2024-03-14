package com.marchenaya.recipebook.navigation.destination

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

private const val HomeRoute = "home"
private const val ShoppingRoute = "shopping"
private const val SettingsRoute = "settings"

sealed class RecipeBookTopLevelDestination : RecipeBookDestination() {

    abstract val selectedIcon: ImageVector

    companion object {
        val bottomBarScreens = listOf(Home, Shopping, Settings)


    }

}

data object Home : RecipeBookTopLevelDestination() {
    override val title: Int
        get() = com.marchenaya.home.R.string.home_title
    override val selectedIcon: ImageVector
        get() = Icons.Rounded.Home
    override val route: String
        get() = HomeRoute

}

data object Shopping : RecipeBookTopLevelDestination() {
    override val title: Int
        get() = com.marchenaya.shopping.R.string.shopping_title
    override val selectedIcon: ImageVector
        get() = Icons.Rounded.Checklist
    override val route: String
        get() = ShoppingRoute
}

data object Settings : RecipeBookTopLevelDestination() {
    override val title: Int
        get() = com.marchenaya.settings.R.string.settings_title
    override val selectedIcon: ImageVector
        get() = Icons.Rounded.Settings
    override val route: String
        get() = SettingsRoute
}


