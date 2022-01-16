package com.example.starwars.infra.models.planet

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetsResponse (
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String,
    @SerialName("previous") val previous: String? = null,
    @SerialName("results") val results: ArrayList<PlanetDetailsResponse>
)