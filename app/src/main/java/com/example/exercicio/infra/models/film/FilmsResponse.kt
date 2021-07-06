package com.example.exercicio.infra.models.film

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmsResponse(
        @SerialName("count") val count: Int? = null,
        @SerialName("results") val result: ArrayList<FilmResponse>
)