package com.marchenaya.data.mapper.database

import android.content.Context
import com.marchenaya.core.model.InstructionModel
import com.marchenaya.data.R
import com.marchenaya.data.database.model.InstructionEntity
import com.marchenaya.data.mapper.base.EntityMapper
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceId
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InstructionEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent,
    @ApplicationContext private val context: Context
) : EntityMapper<InstructionEntity, InstructionModel>() {

    override suspend fun transformEntityToModel(input: InstructionEntity): InstructionModel =
        InstructionModel(
            input.id, input.instruction
        )

    override suspend fun transformModelToEntity(input: InstructionModel): InstructionEntity =
        InstructionEntity(
            input.id, -1, input.instruction, ""
        )

    override fun onMappingError(exception: Exception) {
        traceComponent.traceError(
            TraceId.ENTITY_MAPPER_INSTRUCTION,
            context.getString(R.string.error),
            exception
        )
    }
}