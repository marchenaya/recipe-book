package com.marchenaya.data.remote.model.instruction

import com.google.gson.annotations.SerializedName

data class InstructionStepRemote(
    @SerializedName("number") val id: Int,
    @SerializedName("step") val instruction: String
)
