package feature.characters.data

import com.example.starwars.infra.models.character.CharactersResponse
import retrofit2.http.GET

internal interface CharactersGateway {
    @GET("people")
    suspend fun listCharacters(): CharactersResponse
}