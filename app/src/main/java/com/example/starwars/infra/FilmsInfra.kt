package com.example.starwars.infra

import com.example.starwars.models.Film
import com.example.starwars.models.services.FilmsService
import feature.StarWarsGateway
import feature.utils.RetrofitClient
import io.reactivex.Observable


class FilmsInfra : FilmsService {
    private val api = RetrofitClient.createService(StarWarsGateway::class.java)

    override fun listMovies(): Observable<List<Film>> {
        return api.listMovies().map { filmsResponse ->
            filmsResponse.result.map { filmModel ->
                val id = retrieveFilmId(filmModel.url)
                Film(
                    title = filmModel.title,
                    episodeId = filmModel.episodeId,
                    openingCrawl = filmModel.openingCrawl,
                    director = filmModel.director,
                    producer = filmModel.producer,
                    releaseDate = filmModel.releaseDate,
                    characters = filmModel.characters.orEmpty(),
                    url = retrieveFilmImage(id),
                    id = id
                )
            }
        }
    }

    private fun retrieveFilmId(url: String): String = url.filter { it.isDigit() }

    private fun retrieveFilmImage(id: String): String {
        return "https://starwars-visualguide.com/assets/img/films/$id.jpg"
    }
}