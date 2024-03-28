package com.marchenaya.data.remote.model.instruction

import com.google.gson.annotations.SerializedName

data class InstructionRemote(
    @SerializedName("name") val name: String,
    @SerializedName("steps") val steps: List<InstructionStepRemote>
)
