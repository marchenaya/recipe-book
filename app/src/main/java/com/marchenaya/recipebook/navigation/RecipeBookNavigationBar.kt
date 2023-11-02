package com.marchenaya.recipebook.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.rememberNavController

@Composable
fun RecipeBookNavigationBar(
    destinations: List<RecipeBookDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (RecipeBookDestination) -> Unit
) {
    NavigationBar {
        destinations.forEach { destination ->
            NavigationBarItem(
                icon = {
                    Icon(
                        destination.selectedIcon,
                        contentDescription = stringResource(destination.title)
                    )
                },
                label = { Text(stringResource(destination.title)) },
                selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
                onClick = {
                    onNavigateToDestination(destination)
                }
            )
        }
    }
}

@Preview
@Composable
fun RecipeBookNavigationBarPreview() {
    RecipeBookNavigationBar(
        listOf(Home, Shopping, Settings),
        rememberNavController().currentDestination
    ) {}
}
