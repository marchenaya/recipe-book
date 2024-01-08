package com.marchenaya.home

import androidx.paging.PagingData
import com.marchenaya.domain.model.Recipe

sealed interface RandomRecipesUiState {
    data object Loading : RandomRecipesUiState

    data object Error : RandomRecipesUiState

    data class Success(
        val recipes: PagingData<Recipe> = PagingData.empty()
    ) : RandomRecipesUiState

}