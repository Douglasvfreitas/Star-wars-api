package feature


import feature.character.data.models.CharactersResponse
import feature.film.data.models.FilmDetailsResponse
import feature.film.data.models.FilmsResponse
import feature.planet.data.models.PlanetsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsGateway {

    @GET("films")
    suspend fun listMovies(): FilmsResponse

    @GET("films/{id}")
    fun getMovie(@Path("id") id: Int): Observable<FilmDetailsResponse>

    @GET("people")
    suspend fun getPeople(): CharactersResponse

    @GET("planets")
    fun listPlanet(): Observable<PlanetsResponse>
}