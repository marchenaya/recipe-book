package com.marchenaya.data.mapper.database

import com.marchenaya.core.model.Instruction
import com.marchenaya.core.model.Recipe
import com.marchenaya.data.dispatcher.Dispatcher
import com.marchenaya.data.dispatcher.Dispatchers
import com.marchenaya.data.database.model.InstructionEntity
import com.marchenaya.data.database.model.RecipeEntity
import com.marchenaya.data.database.relation.RecipeWithIngredientsAndInstructions
import com.marchenaya.data.mapper.base.EntityMapper
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceId
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeEntityDataMapper @Inject constructor(
    private val ingredientEntityDataMapper: IngredientEntityDataMapper,
    private val instructionEntityDataMapper: InstructionEntityDataMapper,
    private val traceComponent: TraceComponent,
    @Dispatcher(Dispatchers.Default) private val defaultDispatcher: CoroutineDispatcher,
) : EntityMapper<RecipeWithIngredientsAndInstructions, Recipe>() {

    override suspend fun transformEntityToModel(input: RecipeWithIngredientsAndInstructions): Recipe =
        withContext(defaultDispatcher) {
            Recipe(
                input.recipeEntity.id,
                input.recipeEntity.name,
                input.recipeEntity.imageUrl,
                input.recipeEntity.cookingTime,
                input.recipeEntity.servings,
                ingredientEntityDataMapper.transformEntityList(input.ingredientEntityList),
                input.instructionEntityList.toInstructionModelMap()
            )
        }

    override suspend fun transformModelToEntity(input: Recipe): RecipeWithIngredientsAndInstructions =
        withContext(defaultDispatcher) {
            RecipeWithIngredientsAndInstructions(
                RecipeEntity(
                    input.id, input.title, input.imageUrl, input.cookingTime, input.servings
                ),
                ingredientEntityDataMapper.transformModelList(input.ingredients)
                    .map { ingredientEntity ->
                        ingredientEntity.copy(recipeId = input.id)
                    },
                input.instructions.toInstructionEntityList(input.id)
            )
        }

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.ENTITY_MAPPER_RECIPE, "error", error)
    }

    private suspend fun List<InstructionEntity>.toInstructionModelMap(): Map<String, List<Instruction>> =
        withContext(defaultDispatcher) {
            map { instructionEntity ->
                instructionEntity.instructionName to
                        instructionEntityDataMapper.transformEntityToModel(instructionEntity)
            }.groupBy({ it.first }, { it.second })
        }

    private suspend fun Map<String, List<Instruction>>.toInstructionEntityList(recipeId: Int): List<InstructionEntity> =
        withContext(defaultDispatcher) {
            flatMap { (instructionName, instructions) ->
                instructions.map { instruction ->
                    InstructionEntity(
                        instruction.id,
                        recipeId,
                        instruction.instruction,
                        instructionName
                    )
                }
            }
        }

}