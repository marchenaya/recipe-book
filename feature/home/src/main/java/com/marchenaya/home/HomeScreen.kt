package com.marchenaya.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.marchenaya.core.model.Recipe
import com.marchenaya.core.ui.component.RecipeBookCardList
import com.marchenaya.core.ui.component.RecipeBookText
import com.marchenaya.core.ui.theme.RecipeBookTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    @StringRes title: Int,
    onSearchClick: () -> Unit,
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val randomRecipesPagingItems: LazyPagingItems<Recipe> =
        viewModel.randomRecipesUiState.collectAsLazyPagingItems()

    HomeContentScreen(
        title = title,
        randomRecipesPagingItems = randomRecipesPagingItems,
        onSearchClick = onSearchClick,
        onRecipeClick = onRecipeClick,
        modifier = modifier
    )

}

@Composable
fun HomeContentScreen(
    @StringRes title: Int,
    randomRecipesPagingItems: LazyPagingItems<Recipe>,
    onSearchClick: () -> Unit,
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        RecipeBookText(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier.padding(all = 16.dp)
        )

        RecipeBookCardList(
            items = randomRecipesPagingItems,
            onRecipeClick
        )

        Button(onClick = { onSearchClick() }) {
            Text(text = "Navigate to Search")
        }

    }
}

@Preview
@Composable
fun HomeContentScreenPreview() {
    RecipeBookTheme {
        HomeContentScreen(
            R.string.home_title,
            flowOf(
                PagingData.from(
                    MutableList(10) {
                        Recipe(
                            1,
                            stringResource(id = com.marchenaya.ui.R.string.card_text_preview),
                            stringResource(id = com.marchenaya.ui.R.string.image_preview)
                        )
                    }
                )
            ).collectAsLazyPagingItems(), {}, {})
    }
}