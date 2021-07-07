package com.example.exercicio.infra.mappers

import com.example.exercicio.infra.models.FilmsResponse
import com.example.exercicio.models.Film
import io.reactivex.Observable

internal object FilmsMapper {

    operator fun invoke(filmsResponse: FilmsResponse): List<Film> {
        return filmsResponse.result.map { filmResponse ->
            Film(
                title = filmResponse.title,
                episodeId = filmResponse.episodeId,
                openingCrawl = filmResponse.openingCrawl,
                director = filmResponse.director,
                producer = filmResponse.producer,
                releaseDate = filmResponse.releaseDate,
                characters = filmResponse.characters.orEmpty(),
                url = filmResponse.url
            )
        }
    }
}