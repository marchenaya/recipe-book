package com.marchenaya.data.mapper.remote

import android.content.Context
import com.marchenaya.domain.model.InstructionModel
import com.marchenaya.data.R
import com.marchenaya.data.mapper.base.RemoteMapper
import com.marchenaya.data.remote.model.instruction.InstructionStepRemote
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceId
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InstructionRemoteDataMapper @Inject constructor(
    private val traceComponent: TraceComponent,
    @ApplicationContext private val context: Context
) : RemoteMapper<InstructionStepRemote, InstructionModel>() {

    override suspend fun transformModelToRemote(input: InstructionModel): InstructionStepRemote =
        InstructionStepRemote(
            input.id,
            input.instruction
        )

    override suspend fun transformRemoteToModel(input: InstructionStepRemote): InstructionModel =
        InstructionModel(
            input.id,
            input.instruction
        )

    override fun onMappingError(exception: Exception) {
        traceComponent.traceError(
            TraceId.REMOTE_MAPPER_INSTRUCTION,
            context.getString(R.string.error),
            exception
        )
    }

}