package com.marchenaya.data.mapper.remote

import android.content.Context
import com.marchenaya.core.model.Instruction
import com.marchenaya.core.model.Recipe
import com.marchenaya.data.R
import com.marchenaya.data.dispatcher.Dispatcher
import com.marchenaya.data.dispatcher.Dispatchers
import com.marchenaya.data.mapper.base.RemoteMapper
import com.marchenaya.data.remote.model.instruction.InstructionRemote
import com.marchenaya.data.remote.model.instruction.InstructionStepRemote
import com.marchenaya.data.remote.model.recipe.RecipeRemote
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceId
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRemoteDataMapper @Inject constructor(
    private val ingredientRemoteDataMapper: IngredientRemoteDataMapper,
    private val instructionRemoteDataMapper: InstructionRemoteDataMapper,
    private val traceComponent: TraceComponent,
    @Dispatcher(Dispatchers.Default) private val defaultDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context
) : RemoteMapper<RecipeRemote, Recipe>() {

    override suspend fun transformModelToRemote(input: Recipe): RecipeRemote =
        withContext(defaultDispatcher) {
            RecipeRemote(
                input.id,
                input.title,
                input.imageUrl,
                input.cookingTime,
                input.servings,
                ingredientRemoteDataMapper.transformModelList(input.ingredients),
                input.instructions.toInstructionRemoteList()
            )
        }

    override suspend fun transformRemoteToModel(input: RecipeRemote): Recipe =
        withContext(defaultDispatcher) {
            Recipe(
                input.id,
                input.title,
                input.imageUrl,
                input.cookingTime,
                input.servings,
                ingredientRemoteDataMapper.transformRemoteList(input.ingredients),
                (input.instructions ?: emptyList()).toInstructionModelMap()
            )
        }

    override fun onMappingError(exception: Exception) {
        traceComponent.traceError(TraceId.REMOTE_MAPPER_RECIPE, context.getString(R.string.error), exception)
    }

    private suspend fun Map<String, List<Instruction>>.toInstructionRemoteList(): List<InstructionRemote> =
        withContext(defaultDispatcher) {
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
        }

    private suspend fun List<InstructionRemote>.toInstructionModelMap(): Map<String, List<Instruction>> =
        withContext(defaultDispatcher) {
            associate { instructionRemote ->
                instructionRemote.name to instructionRemote.steps.map { instructionStepRemote ->
                    instructionRemoteDataMapper.transformRemoteToModel(instructionStepRemote)
                }
            }
        }

}