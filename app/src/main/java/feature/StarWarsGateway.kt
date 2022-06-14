package feature


import com.example.starwars.infra.models.film.FilmDetailsResponse
import com.example.starwars.infra.models.film.FilmsResponse
import feature.character.data.models.CharactersResponse
import com.example.starwars.infra.models.planet.PlanetsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsGateway {

    @GET("films")
    fun listMovies(): Observable<FilmsResponse>

    @GET("films/{id}")
    fun getMovie(@Path("id") id: Int) : Observable<FilmDetailsResponse>

    @GET("people")
    suspend fun getPeople(): CharactersResponse

    @GET("planets")
    fun listPlanet(): Observable<PlanetsResponse>
}