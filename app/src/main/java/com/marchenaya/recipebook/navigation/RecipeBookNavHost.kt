package com.marchenaya.recipebook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marchenaya.home.HomeScreen
import com.marchenaya.settings.SettingsScreen
import com.marchenaya.shopping.ShoppingScreen

@Composable
fun RecipeBookNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navHostController, startDestination = Home.route, modifier = modifier) {
        composable(Home.route) {
            HomeScreen()
        }
        composable(Shopping.route) {
            ShoppingScreen()
        }
        composable(Settings.route) {
            SettingsScreen()
        }
    }
}