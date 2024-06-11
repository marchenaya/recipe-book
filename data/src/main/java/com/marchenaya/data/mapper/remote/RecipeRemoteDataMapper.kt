package com.marchenaya.data.mapper.remote

import android.content.Context
import com.marchenaya.domain.model.InstructionModel
import com.marchenaya.domain.model.RecipeModel
import com.marchenaya.data.R
import com.marchenaya.data.mapper.base.RemoteMapper
import com.marchenaya.data.remote.model.instruction.InstructionRemote
import com.marchenaya.data.remote.model.instruction.InstructionStepRemote
import com.marchenaya.data.remote.model.recipe.RecipeRemote
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceId
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RecipeRemoteDataMapper @Inject constructor(
    private val ingredientRemoteDataMapper: IngredientRemoteDataMapper,
    private val instructionRemoteDataMapper: InstructionRemoteDataMapper,
    private val traceComponent: TraceComponent,
    @ApplicationContext private val context: Context
) : RemoteMapper<RecipeRemote, RecipeModel>() {

    override suspend fun transformModelToRemote(input: RecipeModel): RecipeRemote =
        RecipeRemote(
            input.id,
            input.title,
            input.imageUrl,
            input.cookingTime,
            input.servings,
            ingredientRemoteDataMapper.transformModelList(input.ingredientModels),
            input.instructions.toInstructionRemoteList()
        )

    override suspend fun transformRemoteToModel(input: RecipeRemote): RecipeModel =
        RecipeModel(
            input.id,
            input.title,
            input.imageUrl,
            input.cookingTime,
            input.servings,
            ingredientRemoteDataMapper.transformRemoteList(input.ingredients),
            (input.instructions ?: emptyList()).toInstructionModelMap()
        )

    override fun onMappingError(exception: Exception) {
        traceComponent.traceError(
            TraceId.REMOTE_MAPPER_RECIPE,
            context.getString(R.string.error),
            exception
        )
    }

    private fun Map<String, List<InstructionModel>>.toInstructionRemoteList(): List<InstructionRemote> =
        map { (instructionName, instructions) ->
            InstructionRemote(
                instructionName,
                instructions.map { instruction ->
                    InstructionStepRemote(
                        instruction.id,
                        instruction.instruction
                    )
                }
            )
        }

    private suspend fun List<InstructionRemote>.toInstructionModelMap(): Map<String, List<InstructionModel>> =
        associate { instructionRemote ->
            instructionRemote.name to instructionRemote.steps.map { instructionStepRemote ->
                instructionRemoteDataMapper.transformRemoteToModel(instructionStepRemote)
            }
        }

}