package com.marchenaya.data.mapper.database

import android.content.Context
import com.marchenaya.core.model.InstructionModel
import com.marchenaya.core.model.RecipeModel
import com.marchenaya.data.R
import com.marchenaya.data.database.model.InstructionEntity
import com.marchenaya.data.database.model.RecipeEntity
import com.marchenaya.data.database.relation.RecipeWithIngredientsAndInstructions
import com.marchenaya.data.mapper.base.EntityMapper
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceId
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RecipeEntityDataMapper @Inject constructor(
    private val ingredientEntityDataMapper: IngredientEntityDataMapper,
    private val instructionEntityDataMapper: InstructionEntityDataMapper,
    private val traceComponent: TraceComponent,
    @ApplicationContext private val context: Context
) : EntityMapper<RecipeWithIngredientsAndInstructions, RecipeModel>() {

    override suspend fun transformEntityToModel(input: RecipeWithIngredientsAndInstructions): RecipeModel =
        RecipeModel(
            input.recipeEntity.id,
            input.recipeEntity.name,
            input.recipeEntity.imageUrl,
            input.recipeEntity.cookingTime,
            input.recipeEntity.servings,
            ingredientEntityDataMapper.transformEntityList(input.ingredientEntityList),
            input.instructionEntityList.toInstructionModelMap()
        )


    override suspend fun transformModelToEntity(input: RecipeModel): RecipeWithIngredientsAndInstructions =
        RecipeWithIngredientsAndInstructions(
            RecipeEntity(
                input.id, input.title, input.imageUrl, input.cookingTime, input.servings
            ),
            ingredientEntityDataMapper.transformModelList(input.ingredientModels)
                .map { ingredientEntity ->
                    ingredientEntity.copy(recipeId = input.id)
                },
            input.instructions.toInstructionEntityList(input.id)
        )

    override fun onMappingError(exception: Exception) {
        traceComponent.traceError(
            TraceId.ENTITY_MAPPER_RECIPE,
            context.getString(R.string.error),
            exception
        )
    }

    private suspend fun List<InstructionEntity>.toInstructionModelMap(): Map<String, List<InstructionModel>> =
        map { instructionEntity ->
            instructionEntity.instructionName to
                    instructionEntityDataMapper.transformEntityToModel(instructionEntity)
        }.groupBy({ pair -> pair.first }, { pair -> pair.second })

    private fun Map<String, List<InstructionModel>>.toInstructionEntityList(recipeId: Int): List<InstructionEntity> =
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