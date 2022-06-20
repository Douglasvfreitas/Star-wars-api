package feature.planet.domain

import feature.ImageTypeResponse.PLANET_TYPE
import feature.planet.data.models.PlanetDetailsResponse
import feature.planet.data.models.PlanetsResponse
import feature.planet.domain.models.PlanetsPresentation
import feature.utils.retrieveIdForImage
import feature.utils.retrieveImageById

internal object PlanetsMapper {
    fun toDomain(response: PlanetsResponse): PlanetsPresentation =
        PlanetsPresentation(
            count = response.count,
            next = response.next,
            previous = response.previous,
            planets = getPlanets(response.results)
        )

    private fun getPlanets(response:List<PlanetDetailsResponse>): List<PlanetDetailsResponse> {
        return response.map { planetDetailsResponse ->
            val planetId = retrieveIdForImage(planetDetailsResponse.url)
            planetDetailsResponse.copy(
                url = retrieveImageById(planetId, PLANET_TYPE)
            )
        }
    }
}