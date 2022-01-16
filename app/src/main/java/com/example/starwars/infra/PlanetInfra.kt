package com.example.starwars.infra

import com.example.starwars.infra.models.planet.PlanetsPresentation
import com.example.starwars.models.Planet
import com.example.starwars.models.services.PlanetService
import io.reactivex.Observable

class PlanetInfra : PlanetService {
    private val api = RetrofitClient.createService(StarWarsGateway::class.java)


    override fun listPlanet(): Observable<PlanetsPresentation> {
        return api.listPlanet().map { planetsResponse ->
            PlanetsPresentation(
                count = planetsResponse.count,
                previous = planetsResponse.previous,
                next = planetsResponse.next,
                planets = planetsResponse.results.map { planetResponse ->
                    val id = retrievePlanetId(planetResponse.url)
                    Planet(
                        name = planetResponse.name,
                        rotation = planetResponse.rotation,
                        orbital = planetResponse.orbital,
                        diameter = planetResponse.diameter,
                        climate = planetResponse.climate,
                        gravity = planetResponse.gravity,
                        terrain = planetResponse.terrain,
                        water = planetResponse.water,
                        population = planetResponse.population,
                        urlImage = retrievePlanetImage(id),
                        id = id
                    )
                }
            )
        }
    }

    private fun retrievePlanetId(url: String): String = url.filter { it.isDigit() }

    private fun retrievePlanetImage(id: String): String {
        return "https://starwars-visualguide.com/assets/img/planets/$id.jpg"
    }
}