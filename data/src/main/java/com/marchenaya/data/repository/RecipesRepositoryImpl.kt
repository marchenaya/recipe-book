package com.marchenaya.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.marchenaya.core.model.Ingredient
import com.marchenaya.core.model.Instruction
import com.marchenaya.core.model.Recipe
import com.marchenaya.data.Dispatcher
import com.marchenaya.data.Dispatchers
import com.marchenaya.data.remote.datasource.RecipesRemoteDataSource
import com.marchenaya.data.remote.model.ingredient.IngredientRemote
import com.marchenaya.data.remote.model.instruction.InstructionRemote
import com.marchenaya.data.remote.model.instruction.InstructionStepRemote
import com.marchenaya.data.remote.model.recipe.RecipeRemote
import com.marchenaya.data.remote.pagingsource.RandomRecipePagingSource
import com.marchenaya.domain.repository.RecipesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    @Dispatcher(Dispatchers.Default) private val defaultDispatcher: CoroutineDispatcher,
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val randomRecipePagingSource: RandomRecipePagingSource,
    private val remoteDataSource: RecipesRemoteDataSource
) : RecipesRepository {

    override fun getRandomRecipes(): Flow<PagingData<Recipe>> {
        return Pager(
            config = PagingConfig(NETWORK_PAGE_SIZE),
            pagingSourceFactory = { randomRecipePagingSource }
        ).flow.map { pagingData ->
            pagingData.map { recipeRemote -> recipeRemote.toRecipeDomain() }
        }.cachedIn(CoroutineScope(ioDispatcher))
    }

    override suspend fun getRecipeById(id: Int): Recipe? {
        return remoteDataSource.getRecipeById(id)?.toRecipeDomain()
    }

    private suspend fun RecipeRemote.toRecipeDomain(): Recipe =
        withContext(defaultDispatcher) {
            Recipe(
                id,
                title,
                imageUrl,
                cookingTime,
                servings,
                ingredients.map { ingredientRemote -> ingredientRemote.toIngredientDomain() },
                instructions.toInstructionDomainMap()
            )
        }

    private suspend fun IngredientRemote.toIngredientDomain(): Ingredient =
        withContext(defaultDispatcher) {
            Ingredient(
                id,
                name,
                "${
                    measures.metricMeasure.amount.toString().replace(".0", "")
                } ${measures.metricMeasure.unit}".trim()
            )
        }

    private suspend fun List<InstructionRemote>.toInstructionDomainMap(): Map<String, List<Instruction>> =
        withContext(defaultDispatcher) {
            associate { instructionRemote ->
                instructionRemote.name to instructionRemote.steps.map { instructionStepRemote ->
                    instructionStepRemote.toInstructionDomain()
                }
            }
        }

    private fun InstructionStepRemote.toInstructionDomain() =
        Instruction(
            id, instruction
        )

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

}