package com.marchenaya.data.mapper.remote

import android.content.Context
import com.marchenaya.domain.model.IngredientModel
import com.marchenaya.data.R
import com.marchenaya.data.mapper.base.RemoteMapper
import com.marchenaya.data.remote.model.ingredient.IngredientMeasureRemote
import com.marchenaya.data.remote.model.ingredient.IngredientMetricMeasureRemote
import com.marchenaya.data.remote.model.ingredient.IngredientRemote
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceId
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class IngredientRemoteDataMapper @Inject constructor(
    private val traceComponent: TraceComponent,
    @ApplicationContext private val context: Context
) : RemoteMapper<IngredientRemote, IngredientModel>() {

    override suspend fun transformModelToRemote(input: IngredientModel): IngredientRemote {
        val amount = input.amount.trim().split(" ")[0].toDouble()
        val unit = input.amount.trim().split(" ")[1]
        return IngredientRemote(
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


    override suspend fun transformRemoteToModel(input: IngredientRemote): IngredientModel =
        IngredientModel(
            input.id,
            input.name,
            "${
                input.measures.metricMeasure.amount.toString().replace(".0", "")
            } ${input.measures.metricMeasure.unit}".trim()
        )


    override fun onMappingError(exception: Exception) {
        traceComponent.traceError(
            TraceId.REMOTE_MAPPER_INGREDIENT,
            context.getString(R.string.error),
            exception
        )
    }

}