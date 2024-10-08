package com.example.majkomulti.data.models.NoteData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NoteData(
    @SerialName("taskId") val taskId: String,
    @SerialName("text") val text: String
)