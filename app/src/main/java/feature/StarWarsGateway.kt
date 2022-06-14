package feature


import feature.film.data.models.FilmDetailsResponse
import feature.film.data.models.FilmsResponse
import feature.character.data.models.CharactersResponse
import feature.planet.data.models.PlanetsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsGateway {

    @GET("films")
    fun listMovies(): Observable<FilmsResponse>

    @GET("films/{id}")
    fun getMovie(@Path("id") id: Int) : Observable<FilmDetailsResponse>

    @GET("people")
    fun listPeople(): Observable<CharactersResponse>

    @GET("planets")
    fun listPlanet(): Observable<PlanetsResponse>
}