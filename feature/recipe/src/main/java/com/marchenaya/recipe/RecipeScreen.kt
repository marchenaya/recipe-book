package com.marchenaya.recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marchenaya.core.model.Recipe
import com.marchenaya.core.ui.component.RecipeBookIconText
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
            stringResource(id = com.marchenaya.ui.R.string.image_preview),
            cookingTime = 160,
            2
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
            Row(
                Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RecipeBookIconText(
                    text = recipe.cookingTimeToHoursAndMinutes(),
                    icon = Icons.Rounded.Timer,
                    iconContentDescription = stringResource(R.string.preparation_time_icon_description),
                    modifier = Modifier.padding(start = 8.dp)
                )
                RecipeBookIconText(
                    text = stringResource(
                        R.string.servings_template,
                        recipe.servings
                    ),
                    icon = ImageVector.vectorResource(id = com.marchenaya.ui.R.drawable.ic_people_24dp),
                    iconContentDescription = stringResource(R.string.servings_icon_description)
                )
            }
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