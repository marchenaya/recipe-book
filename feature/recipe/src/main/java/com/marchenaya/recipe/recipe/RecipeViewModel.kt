package com.marchenaya.recipe.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marchenaya.core.model.exception.recipe.RecipeIdIsNullException
import com.marchenaya.core.model.exception.recipe.RecipeIsNullException
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
                    _uiState.emit(RecipeUiState.Error(RecipeIsNullException()))
                }
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