package com.marchenaya.recipebook.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.marchenaya.recipebook.navigation.RecipeBookDestination
import com.marchenaya.recipebook.navigation.RecipeBookNavHost
import com.marchenaya.recipebook.navigation.RecipeBookNavigationBar

@Composable
fun RecipeBookApp() {
    RecipeBookTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination

        Scaffold(
            bottomBar = {
                RecipeBookNavigationBar(
                    destinations = RecipeBookDestination.bottomBarScreens,
                    currentDestination = currentDestination,
                    onNavigateToDestination = { destination ->
                        navController.navigate(destination.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        ) { innerPadding ->
            RecipeBookNavHost(
                navHostController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}