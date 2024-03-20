package com.marchenaya.recipe.recipe

import com.marchenaya.core.model.Recipe

sealed interface RecipeUiState {

    data class Success(val recipe: Recipe) : RecipeUiState

    data object Loading : RecipeUiState

    data class Error(val exception: Exception) : RecipeUiState

}