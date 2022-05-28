package feature.characters.data

import feature.characters.data.models.CharactersResponse
import retrofit2.http.GET

internal interface CharactersGateway {
    @GET("people")
    suspend fun listCharacters(): CharactersResponse
}