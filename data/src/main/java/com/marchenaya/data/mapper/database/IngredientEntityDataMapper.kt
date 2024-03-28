package com.marchenaya.data.mapper.database

import android.content.Context
import com.marchenaya.core.model.IngredientModel
import com.marchenaya.data.R
import com.marchenaya.data.database.model.IngredientEntity
import com.marchenaya.data.mapper.base.EntityMapper
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceId
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class IngredientEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent,
    @ApplicationContext private val context: Context
) : EntityMapper<IngredientEntity, IngredientModel>() {

    override suspend fun transformEntityToModel(input: IngredientEntity): IngredientModel =
        IngredientModel(
            input.id, input.name, input.amount
        )

    override suspend fun transformModelToEntity(input: IngredientModel): IngredientEntity =
        IngredientEntity(
            input.id, -1, input.name, input.amount
        )

    override fun onMappingError(exception: Exception) {
        traceComponent.traceError(
            TraceId.ENTITY_MAPPER_INGREDIENT,
            context.getString(R.string.error), exception
        )
    }
}