package com.marchenaya.domain.usecase

import androidx.paging.PagingData
import com.marchenaya.core.model.Recipe
import com.marchenaya.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) {

    operator fun invoke(): Flow<PagingData<Recipe>> =
        recipesRepository.getRandomRecipes()

}