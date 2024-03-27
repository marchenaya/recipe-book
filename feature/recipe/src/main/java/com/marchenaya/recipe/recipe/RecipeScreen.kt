package com.marchenaya.recipe.recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marchenaya.core.model.Recipe
import com.marchenaya.core.ui.component.RecipeBookIconText
import com.marchenaya.core.ui.component.RecipeBookLargeTopBar
import com.marchenaya.core.ui.component.RecipeBookText
import com.marchenaya.ui.R

@Composable
fun RecipeScreen(
    recipeId: Int?,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecipeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(recipeId) {
        if (recipeId != null) {
            viewModel.getRecipeById(recipeId)
        } else {
            viewModel.notifyRecipeIdIsNull()
        }
    }

    RecipeScreenContent(
        uiState,
        onBackClick = { onBackClick() },
        modifier
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreenContent(
    uiState: RecipeUiState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )

    when (uiState) {
        is RecipeUiState.Success ->
            Scaffold(
                topBar = {
                    RecipeBookLargeTopBar(
                        onNavigationClick = { onBackClick() },
                        scrollBehavior = scrollBehavior,
                        title = uiState.recipe.title,
                        imageUrl = uiState.recipe.imageUrl ?: ""
                    )
                },
                modifier = modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
            ) { innerPadding ->
                SuccessContent(
                    modifier,
                    innerPadding,
                    uiState.recipe
                )
            }


        is RecipeUiState.Loading -> LoadingContent()
        is RecipeUiState.Error -> ErrorContent(uiState.exception)
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier,
    innerPadding: PaddingValues,
    recipe: Recipe
) {
    val instructions = recipe.instructions
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
                    text = recipe.cookingTimeToHoursAndMinutes(
                        stringResource(com.marchenaya.recipe.R.string.hour_abbreviation),
                        stringResource(com.marchenaya.recipe.R.string.minute_abbreviation)
                    ),
                    icon = Icons.Rounded.Timer,
                    iconContentDescription = stringResource(com.marchenaya.recipe.R.string.preparation_time_icon_description),
                    modifier = Modifier.padding(start = 8.dp)
                )
                RecipeBookIconText(
                    text = stringResource(
                        com.marchenaya.recipe.R.string.servings_template,
                        recipe.servings
                    ),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_people_24dp),
                    iconContentDescription = stringResource(com.marchenaya.recipe.R.string.servings_icon_description)
                )
            }
            HorizontalDivider(Modifier.padding(top = 16.dp))
            RecipeBookText(
                text = stringResource(com.marchenaya.recipe.R.string.ingredients),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp, top = 16.dp)
            )
        }
        items(recipe.ingredients) { ingredient ->
            Text(
                text = "${ingredient.amount} ${ingredient.name}",
                Modifier.padding(horizontal = 8.dp)
            )
        }
        if (instructions.isNotEmpty()) {
            item {
                HorizontalDivider(Modifier.padding(top = 16.dp))
                RecipeBookText(
                    text = stringResource(com.marchenaya.recipe.R.string.instructions),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(
                        bottom = 8.dp,
                        start = 8.dp,
                        end = 8.dp,
                        top = 16.dp
                    )
                )
            }
            items(instructions.keys.toList()) { instructionsName ->
                if (instructionsName != "") {
                    HorizontalDivider(
                        Modifier
                            .padding(top = 8.dp)
                            .padding(8.dp)
                    )
                    RecipeBookText(
                        text = stringResource(
                            com.marchenaya.recipe.R.string.instruction_name_placeholder,
                            instructionsName
                        ),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(top = 8.dp)
                    )
                }
                instructions[instructionsName]?.forEach { instruction ->
                    RecipeBookText(
                        text = stringResource(
                            com.marchenaya.recipe.R.string.instruction_step_placeholder,
                            instruction.id
                        ),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(top = 8.dp)
                    )
                    Text(
                        text = instruction.instruction,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorContent(exception: Exception) {
    Text(text = exception.message ?: "")
}

@Preview
@Composable
fun RecipeScreenPreview() {
    RecipeScreen(
        recipeId = 0,
        onBackClick = { }
    )
}