package com.marchenaya.recipebook.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.marchenaya.core.ui.theme.RecipeBookTheme
import com.marchenaya.recipebook.navigation.destination.Recipe
import com.marchenaya.recipebook.navigation.destination.RecipeBookDestination
import com.marchenaya.recipebook.navigation.destination.RecipeBookTopLevelDestination
import com.marchenaya.recipebook.navigation.navgraph.RecipeBookNavHost
import com.marchenaya.recipebook.ui.bar.bottom.RecipeBookBottomBar
import com.marchenaya.recipebook.ui.bar.top.RecipeBookTopBar

@Composable
fun RecipeBookApp() {
    RecipeBookTheme {
        val navHostController = rememberNavController()
        val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination by remember {
            derivedStateOf {
                currentBackStackEntry?.destination?.route?.let(RecipeBookDestination::getByRoute)
            }
        }

        Scaffold(
            topBar = {
                if (currentDestination != Recipe) {
                    RecipeBookTopBar(
                        currentDestination = currentDestination,
                        onNavigationClick = {
                            navHostController.popBackStack()
                        }
                    )
                }
            },
            bottomBar = {
                RecipeBookBottomBar(
                    destinations = RecipeBookTopLevelDestination.bottomBarScreens,
                    currentDestination = currentDestination,
                    onNavigateToDestination = { destination ->
                        navHostController.navigate(destination.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
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
                navHostController = navHostController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}