package com.example.exercicio.infra

import com.example.exercicio.models.Film
import com.example.exercicio.models.services.FilmsService
import io.reactivex.Observable


class FilmsInfra : FilmsService {
    private val api = RetrofitClient.createService(StarWarsGateway::class.java)

    override fun listMovies(): Observable<List<Film>> {
        return api.listMovies().map { filmsResponse ->
            filmsResponse.result.map { filmModel ->
                Film(
                    title = filmModel.title,
                    episodeId = filmModel.episodeId,
                    openingCrawl = filmModel.openingCrawl,
                    director = filmModel.director,
                    producer = filmModel.producer,
                    releaseDate = filmModel.releaseDate,
                    characters = filmModel.characters.orEmpty(),
                    url = filmModel.url
                )
            }
        }
    }

    private fun retrieveFilmId(url: String): String = url.filter { it.isDigit() }

    private fun retrieveFilmImage(id: String): String {
        return "https://starwars-visualguide.com/assets/img/films/$id.jpg"
    }
}