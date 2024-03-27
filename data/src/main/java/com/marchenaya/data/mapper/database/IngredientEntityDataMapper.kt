package com.marchenaya.data.mapper.database

import android.content.Context
import com.marchenaya.core.model.Ingredient
import com.marchenaya.data.R
import com.marchenaya.data.database.model.IngredientEntity
import com.marchenaya.data.dispatcher.Dispatcher
import com.marchenaya.data.dispatcher.Dispatchers
import com.marchenaya.data.mapper.base.EntityMapper
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceId
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IngredientEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent,
    @Dispatcher(Dispatchers.Default) private val defaultDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context
) : EntityMapper<IngredientEntity, Ingredient>() {

    override suspend fun transformEntityToModel(input: IngredientEntity): Ingredient =
        withContext(defaultDispatcher) {
            Ingredient(
                input.id, input.name, input.amount
            )
        }

    override suspend fun transformModelToEntity(input: Ingredient): IngredientEntity =
        withContext(defaultDispatcher) {
            IngredientEntity(
                input.id, -1, input.name, input.amount
            )
        }

    override fun onMappingError(exception: Exception) {
        traceComponent.traceError(
            TraceId.ENTITY_MAPPER_INGREDIENT,
            context.getString(R.string.error), exception
        )
    }
}