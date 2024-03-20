package com.marchenaya.domain.usecase

import com.marchenaya.core.model.Recipe
import com.marchenaya.domain.repository.RecipesRepository
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) {

    suspend operator fun invoke(id: Int): Recipe? =
        recipesRepository.getRecipeById(id)

}