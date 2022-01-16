package com.example.starwars.models.services

import com.example.starwars.models.Film
import io.reactivex.Observable

interface FilmsService {
    fun listMovies() : Observable<List<Film>>
}