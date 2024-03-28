package com.marchenaya.recipe.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marchenaya.domain.usecase.GetRecipeByIdUseCase
import com.marchenaya.recipe.recipe.exception.RecipeIdIsNullException
import com.marchenaya.recipe.recipe.exception.RecipeIsNullException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getRecipeById(id: Int) {
        viewModelScope.launch {
            try {
                getRecipeByIdUseCase(id)?.let { recipeModel ->
                    _uiState.emit(RecipeUiState.Success(recipeModel))
                } ?: _uiState.emit(RecipeUiState.Error(RecipeIsNullException()))
            } catch (exception: Exception) {
                _uiState.emit(RecipeUiState.Error(exception))
            }
        }
    }

    fun notifyRecipeIdIsNull() {
        viewModelScope.launch {
            _uiState.emit(RecipeUiState.Error(RecipeIdIsNullException()))
        }
    }

}