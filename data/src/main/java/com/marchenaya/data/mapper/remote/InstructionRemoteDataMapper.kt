package com.marchenaya.data.mapper.remote

import android.content.Context
import com.marchenaya.core.model.Instruction
import com.marchenaya.data.R
import com.marchenaya.data.dispatcher.Dispatcher
import com.marchenaya.data.dispatcher.Dispatchers
import com.marchenaya.data.mapper.base.RemoteMapper
import com.marchenaya.data.remote.model.instruction.InstructionStepRemote
import com.marchenaya.data.trace.TraceComponent
import com.marchenaya.data.trace.TraceId
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InstructionRemoteDataMapper @Inject constructor(
    private val traceComponent: TraceComponent,
    @Dispatcher(Dispatchers.Default) private val defaultDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context
) : RemoteMapper<InstructionStepRemote, Instruction>() {

    override suspend fun transformModelToRemote(input: Instruction): InstructionStepRemote =
        withContext(defaultDispatcher) {
            InstructionStepRemote(
                input.id,
                input.instruction
            )
        }


    override suspend fun transformRemoteToModel(input: InstructionStepRemote): Instruction =
        withContext(defaultDispatcher) {
            Instruction(
                input.id,
                input.instruction
            )
        }


    override fun onMappingError(exception: Exception) {
        traceComponent.traceError(TraceId.REMOTE_MAPPER_INSTRUCTION, context.getString(R.string.error), exception)
    }

}