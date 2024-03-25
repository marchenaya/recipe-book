package com.marchenaya.data.mapper.remote

import com.marchenaya.core.model.Ingredient
import com.marchenaya.data.dispatcher.Dispatcher
import com.marchenaya.data.dispatcher.Dispatchers
import com.marchenaya.data.mapper.base.RemoteMapper
import com.marchenaya.data.remote.model.ingredient.IngredientMeasureRemote
import com.marchenaya.data.remote.model.ingredient.IngredientMetricMeasureRemote
import com.marchenaya.data.remote.model.ingredient.IngredientRemote
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceId
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IngredientRemoteDataMapper @Inject constructor(
    private val traceComponent: TraceComponent,
    @Dispatcher(Dispatchers.Default) private val defaultDispatcher: CoroutineDispatcher,
) : RemoteMapper<IngredientRemote, Ingredient>() {

    override suspend fun transformModelToRemote(input: Ingredient): IngredientRemote =
        withContext(defaultDispatcher) {
            val amount = input.amount.split(" ")[0].toDouble()
            val unit = input.amount.split(" ")[1]
            IngredientRemote(
                input.id,
                input.name,
                IngredientMeasureRemote(
                    IngredientMetricMeasureRemote(
                        amount,
                        unit
                    )
                )
            )
        }


    override suspend fun transformRemoteToModel(input: IngredientRemote): Ingredient =
        withContext(defaultDispatcher) {
            Ingredient(
                input.id,
                input.name,
                "${
                    input.measures.metricMeasure.amount.toString().replace(".0", "")
                } ${input.measures.metricMeasure.unit}".trim()
            )
        }


    override fun onMappingError(exception: Exception) {
        traceComponent.traceError(TraceId.REMOTE_MAPPER_INGREDIENT, "error", exception)
    }

}