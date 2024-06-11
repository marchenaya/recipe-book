package com.marchenaya.domain.usecase

import androidx.paging.PagingData
import com.marchenaya.domain.model.RecipeModel
import com.marchenaya.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipeRepository
) {

    operator fun invoke(): Flow<PagingData<RecipeModel>> =
        recipesRepository.getRandomRecipes()

}