package com.marchenaya.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.marchenaya.domain.model.Recipe
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val randomRecipesPagingItems: LazyPagingItems<Recipe> =
        viewModel.randomRecipesUiState.collectAsLazyPagingItems()

    HomeContentScreen(
        randomRecipesPagingItems = randomRecipesPagingItems,
        onSearchClick = onSearchClick,
        modifier = modifier
    )

}

@Composable
fun HomeContentScreen(
    randomRecipesPagingItems: LazyPagingItems<Recipe>,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "Hello HomeScreen!",
            modifier = modifier
        )
        Button(onClick = { onSearchClick() }) {
            Text(text = "Navigate to Search")
        }

        when (randomRecipesPagingItems.loadState.refresh) {
            LoadState.Loading -> {
                CircularProgressIndicator()
            }

            is LoadState.Error -> {
                RecipeBookText(text = "There is an error")
            }

            is LoadState.NotLoading -> {
                if (randomRecipesPagingItems.itemCount > 0) {
                    LazyRow {
                        items(randomRecipesPagingItems.itemCount) { index ->
                            RecipeBookText(
                                text = randomRecipesPagingItems[index]!!.title
                            )
                        }
                    }
                } else {
                    RecipeBookText(text = "There is no recipes")
                }
            }
        }
    }
}

@Composable
fun RecipeBookText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary
    )
}

@Preview
@Composable
fun HomeContentScreenPreview() {
    HomeContentScreen(
        flowOf(
            PagingData.from(
                listOf(
                    Recipe(
                        1,
                        "test",
                        "image"
                    )
                )
            )
        ).collectAsLazyPagingItems(), {})
}