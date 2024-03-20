package com.marchenaya.recipe.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marchenaya.domain.usecase.GetRecipeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val useCase: GetRecipeByIdUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getRecipeById(id: Int) {
        viewModelScope.launch {
            try {
                val recipe = useCase(id)
                if (recipe != null) {
                    _uiState.emit(RecipeUiState.Success(recipe))
                } else {
                    _uiState.emit(RecipeUiState.Error(IllegalStateException("Recipe is null")))
                }
            } catch (e: Exception) {
                _uiState.emit(RecipeUiState.Error(e))
            }
        }
    }

    fun notifyRecipeIdIsNull() {
        viewModelScope.launch {
            _uiState.emit(RecipeUiState.Error(IllegalStateException("Recipe id is null")))
        }
    }

}