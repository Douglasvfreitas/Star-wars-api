package feature.planet.domain.models

data class PlanetsPresentation(
    val count: Int,
    val next: String,
    val previous: String? = null,
    val planets: List<Planet>
)