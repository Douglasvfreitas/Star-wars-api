package feature.planet.domain

import feature.planet.domain.models.PlanetsPresentation
import io.reactivex.Observable

interface PlanetService {
    fun listPlanet(): Observable<PlanetsPresentation>
}