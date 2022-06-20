package feature.planet.data

import feature.planet.domain.models.PlanetsPresentation
import feature.StarWarsGateway
import feature.planet.domain.PlanetsMapper
import feature.utils.RetrofitClient
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
internal class PlanetInfra {
    private val api = RetrofitClient.createService(StarWarsGateway::class.java)

    suspend fun listPlanet(): PlanetsPresentation {
        val response = api.listPlanet()
        return PlanetsMapper.toDomain(response)
    }
}