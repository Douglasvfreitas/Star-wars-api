package feature.planet.domain.models

import feature.planet.data.models.PlanetDetailsResponse

data class PlanetsPresentation(
    val count: Int,
    val next: String,
    val previous: String? = null,
    val planets: List<PlanetDetailsResponse>
)