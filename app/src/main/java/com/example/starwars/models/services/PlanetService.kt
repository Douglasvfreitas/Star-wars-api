package com.example.starwars.models.services

import com.example.starwars.infra.models.planet.PlanetsPresentation
import io.reactivex.Observable

interface PlanetService {
    fun listPlanet(): Observable<PlanetsPresentation>
}