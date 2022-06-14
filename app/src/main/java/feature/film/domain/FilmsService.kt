package feature.film.domain

import feature.film.domain.models.Film
import io.reactivex.Observable

interface FilmsService {
    fun listMovies() : Observable<List<Film>>
}