package com.example.exercicio.models.services

import com.example.exercicio.infra.models.planet.PlanetsPresentation
import io.reactivex.Observable

interface PlanetService {
    fun listPlanet(): Observable<PlanetsPresentation>
}