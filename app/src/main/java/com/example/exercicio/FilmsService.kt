package com.example.exercicio


import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmsService {

    @GET("films")
    fun listMovies(): Observable<FilmsResponse>


    @GET("films/{id}")
    fun getMovie(@Path("id") id: Int) : Observable<FilmResponse>
}