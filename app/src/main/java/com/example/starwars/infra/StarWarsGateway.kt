package com.example.starwars.infra


import com.example.starwars.infra.models.film.FilmDetailsResponse
import com.example.starwars.infra.models.film.FilmsResponse
import com.example.starwars.infra.models.character.CharactersResponse
import com.example.starwars.infra.models.planet.PlanetsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsGateway {

    @GET("films")
    fun listMovies(): Observable<FilmsResponse>

    @GET("films/{id}")
    fun getMovie(@Path("id") id: Int) : Observable<FilmDetailsResponse>

    @GET("people")
    fun listPeople(): Observable<CharactersResponse>

    @GET("planets")
    fun listPlanet(): Observable<PlanetsResponse>
}