package com.marchenaya.recipebook.ui.bar.bottom

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.marchenaya.recipebook.navigation.destination.Home
import com.marchenaya.recipebook.navigation.destination.RecipeBookDestination
import com.marchenaya.recipebook.navigation.destination.RecipeBookTopLevelDestination
import com.marchenaya.recipebook.navigation.destination.Settings
import com.marchenaya.recipebook.navigation.destination.Shopping

@Composable
fun RecipeBookBottomBar(
    destinations: List<RecipeBookTopLevelDestination>,
    currentDestination: RecipeBookDestination?,
    onNavigateToDestination: (RecipeBookTopLevelDestination) -> Unit
) {
    if (currentDestination?.route in destinations.map {
            it.route
        }) {
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
                    selected = currentDestination?.route == destination.route,
                    onClick = {
                        onNavigateToDestination(destination)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun RecipeBookNavigationBarPreview() {
    RecipeBookBottomBar(
        listOf(Home, Shopping, Settings),
        Home
    ) {}
}
