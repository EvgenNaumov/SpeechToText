package com.naumov.mytestapp.model


import com.google.gson.annotations.SerializedName

data class SpeechDTO(
    @SerializedName("result")
    val result: String?
)