package com.example.exercicio.models.services

import com.example.exercicio.models.Film
import io.reactivex.Observable

interface FilmsService {
    fun listMovies() : Observable<List<Film>>
    
}