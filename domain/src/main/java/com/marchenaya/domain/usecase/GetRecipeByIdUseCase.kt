package com.marchenaya.domain.usecase

import com.marchenaya.core.model.Recipe
import com.marchenaya.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val recipesRepository: RecipeRepository
) {

    suspend operator fun invoke(id: Int): Recipe? =
        recipesRepository.getRecipeById(id)

}