package com.marchenaya.data.mapper.database

import android.content.Context
import com.marchenaya.core.model.Instruction
import com.marchenaya.data.R
import com.marchenaya.data.database.model.InstructionEntity
import com.marchenaya.data.dispatcher.Dispatcher
import com.marchenaya.data.dispatcher.Dispatchers
import com.marchenaya.data.mapper.base.EntityMapper
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceId
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InstructionEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent,
    @Dispatcher(Dispatchers.Default) private val defaultDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context
) : EntityMapper<InstructionEntity, Instruction>() {

    override suspend fun transformEntityToModel(input: InstructionEntity): Instruction =
        withContext(defaultDispatcher) {
            Instruction(
                input.id, input.instruction
            )
        }

    override suspend fun transformModelToEntity(input: Instruction): InstructionEntity =
        withContext(defaultDispatcher) {
            InstructionEntity(
                input.id, -1, input.instruction, ""
            )
        }

    override fun onMappingError(exception: Exception) {
        traceComponent.traceError(
            TraceId.ENTITY_MAPPER_INSTRUCTION,
            context.getString(R.string.error),
            exception
        )
    }
}