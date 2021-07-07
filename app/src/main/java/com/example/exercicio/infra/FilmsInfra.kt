package com.example.exercicio.infra

import com.example.exercicio.infra.mappers.FilmsMapper
import com.example.exercicio.models.Film
import com.example.exercicio.models.services.FilmsService
import io.reactivex.Observable


class FilmsInfra : FilmsService {
    private val api = RetrofitClient.createService(StarWarsGateway::class.java)

    override fun listMovies(): Observable<List<Film>> {
        return api.listMovies().map { filmsResponse ->
            FilmsMapper(filmsResponse)
        }
    }
}