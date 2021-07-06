package com.example.exercicio.infra.models.planet

import com.example.exercicio.models.Planet

data class PlanetsPresentation(
    val count: Int,
    val next: String,
    val previous: String? = null,
    val planets: List<Planet>
)