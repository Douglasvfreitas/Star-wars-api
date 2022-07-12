package feature.film.domain

import feature.ImageTypeResponse.FILM_TYPE
import feature.film.data.models.FilmDetailsResponse
import feature.film.data.models.FilmsResponse
import feature.film.domain.models.Film
import feature.film.domain.models.FilmsPresentation
import feature.utils.retrieveIdForImage
import feature.utils.retrieveImageById

object FilmsMapper {
    fun toDomain(response: FilmsResponse): FilmsPresentation =
        FilmsPresentation(
            films = getFilms(response.result),
            cont = response.count ?: 0
        )

    private fun getFilms(response: List<FilmDetailsResponse>): List<Film> =
        response.map { filmDetailsResponse ->
            val filmId = retrieveIdForImage(filmDetailsResponse.url)
            Film(
                title = filmDetailsResponse.title,
                episodeId = filmDetailsResponse.episodeId,
                openingCrawl = filmDetailsResponse.openingCrawl,
                director = filmDetailsResponse.director,
                producer = filmDetailsResponse.producer,
                releaseDate = filmDetailsResponse.releaseDate,
                characters = filmDetailsResponse.characters.orEmpty(),
                url = retrieveImageById(filmId, FILM_TYPE),
                id = filmId
            )
        }
}