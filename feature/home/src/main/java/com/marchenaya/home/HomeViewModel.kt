package com.marchenaya.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.marchenaya.domain.model.Recipe
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

    private val _randomRecipesUiState: MutableStateFlow<PagingData<Recipe>> =
        MutableStateFlow(value = PagingData.empty())
    val randomRecipesUiState: MutableStateFlow<PagingData<Recipe>> get() = _randomRecipesUiState

    init {
        viewModelScope.launch {
            getRandomRecipesUseCase().collectLatest {
                _randomRecipesUiState.emit(it)
            }
        }
    }

}