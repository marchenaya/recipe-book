package com.marchenaya.recipe

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.marchenaya.core.model.Recipe
import com.marchenaya.core.ui.component.RecipeBookLargeTopBar

@Composable
fun RecipeScreen(
    recipeId: Int?,
    onInstructionsClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    RecipeScreenContent(
        recipe = Recipe(
            recipeId ?: 0,
            "My recipe",
            stringResource(id = com.marchenaya.ui.R.string.image_preview)
        ),
        onInstructionsClick = { onInstructionsClick() },
        onBackClick = { onBackClick() }, modifier
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreenContent(
    recipe: Recipe,
    onInstructionsClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )

    Scaffold(
        topBar = {
            RecipeBookLargeTopBar(
                onNavigationClick = { onBackClick() },
                scrollBehavior = scrollBehavior,
                title = recipe.title,
                imageUrl = recipe.imageUrl ?: ""
            )
        },
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->

        ScaffoldContent(
            recipe = recipe,
            onInstructionsClick = { onInstructionsClick() },
            innerPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        )

    }

}

@Composable
fun ScaffoldContent(
    recipe: Recipe,
    onInstructionsClick: () -> Unit,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, contentPadding = innerPadding) {
        item {
            Text(
                text = "Hello RecipeScreen with id : ${recipe.id} !",
                modifier = modifier
            )
            Button(onClick = { onInstructionsClick() }) {
                Text(text = "Navigate to Instructions Screen")
            }
        }
    }
}

@Preview
@Composable
fun RecipeScreenPreview() {
    RecipeScreen(
        recipeId = 0,
        onInstructionsClick = { },
        onBackClick = { }
    )
}