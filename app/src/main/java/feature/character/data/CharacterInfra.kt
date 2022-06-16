package feature.character.data

import feature.StarWarsGateway
import feature.character.domain.CharacterMapper
import feature.character.domain.models.CharactersPresentation
import feature.utils.RetrofitClient

internal class CharacterInfra {
    private val api = RetrofitClient.createService(StarWarsGateway::class.java)

    suspend fun listCharacter(): CharactersPresentation {
        val response = api.getPeople()
        return CharacterMapper.toDomain(response)
    }
}