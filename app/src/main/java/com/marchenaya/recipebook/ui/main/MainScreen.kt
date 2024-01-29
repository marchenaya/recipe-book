package com.marchenaya.recipebook.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.marchenaya.core.ui.theme.RecipeBookTheme
import com.marchenaya.recipebook.navigation.destination.RecipeBookDestination
import com.marchenaya.recipebook.navigation.destination.RecipeBookTopLevelDestination
import com.marchenaya.recipebook.navigation.navgraph.RecipeBookNavHost
import com.marchenaya.recipebook.ui.bar.bottom.RecipeBookBottomBar
import com.marchenaya.recipebook.ui.bar.top.RecipeBookTopBar

@Composable
fun RecipeBookApp() {
    RecipeBookTheme {
        val navController = rememberNavController()
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination by remember {
            derivedStateOf {
                currentBackStackEntry?.destination?.route?.let(RecipeBookDestination::getByRoute)
            }
        }

        Scaffold(
            topBar = {
                RecipeBookTopBar(
                    currentDestination = currentDestination,
                    onNavigationClick = {
                        navController.popBackStack()
                    })
            },
            bottomBar = {
                RecipeBookBottomBar(
                    destinations = RecipeBookTopLevelDestination.bottomBarScreens,
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

@Preview
@Composable
fun RecipeBookAppPreview() {
    RecipeBookApp()
}