package com.example.starwars.infra.models.planet

import com.example.starwars.models.Planet

data class PlanetsPresentation(
    val count: Int,
    val next: String,
    val previous: String? = null,
    val planets: List<Planet>
)