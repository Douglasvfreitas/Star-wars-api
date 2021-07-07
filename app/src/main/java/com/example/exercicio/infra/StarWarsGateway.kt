package com.example.exercicio.infra


import com.example.exercicio.infra.models.FilmResponse
import com.example.exercicio.infra.models.FilmsResponse
import com.example.exercicio.infra.models.character.CharactersResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsGateway {

    @GET("films")
    fun listMovies(): Observable<FilmsResponse>

    @GET("films/{id}")
    fun getMovie(@Path("id") id: Int) : Observable<FilmResponse>

    @GET("people")
    fun listPeople(): Observable<CharactersResponse>
}