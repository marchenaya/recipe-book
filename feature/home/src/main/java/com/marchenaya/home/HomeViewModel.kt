package com.marchenaya.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.marchenaya.core.model.RecipeModel
import com.marchenaya.domain.usecase.GetRandomRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getRandomRecipesUseCase: GetRandomRecipesUseCase
) : ViewModel() {

    private val _randomRecipesUiState: MutableStateFlow<PagingData<RecipeModel>> =
        MutableStateFlow(value = PagingData.empty())
    val randomRecipesUiState: MutableStateFlow<PagingData<RecipeModel>> get() = _randomRecipesUiState

    init {
        viewModelScope.launch {
            getRandomRecipesUseCase().collectLatest { pagingData ->
                _randomRecipesUiState.emit(pagingData)
            }
        }
    }

}