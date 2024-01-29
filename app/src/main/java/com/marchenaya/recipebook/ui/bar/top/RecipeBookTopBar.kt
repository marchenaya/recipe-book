package com.marchenaya.recipebook.ui.bar.top

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.marchenaya.home.R
import com.marchenaya.recipebook.navigation.destination.Home
import com.marchenaya.recipebook.navigation.destination.RecipeBookDestination
import com.marchenaya.recipebook.navigation.destination.RecipeBookTopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBookTopBar(
    currentDestination: RecipeBookDestination?,
    onNavigationClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                stringResource(
                    id = currentDestination?.title ?: R.string.home_title
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            if (currentDestination !in RecipeBookTopLevelDestination.bottomBarScreens) {
                IconButton(onClick = {
                    onNavigationClick()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun RecipeBookTopAppBarPreview() {
    RecipeBookTopBar(currentDestination = Home) {}
}
