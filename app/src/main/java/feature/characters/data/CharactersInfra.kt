package feature.characters.data

import com.example.starwars.infra.RetrofitClient
import com.example.starwars.infra.models.character.CharactersPresentation
import feature.characters.domain.CharacterMapper
import kotlinx.serialization.ExperimentalSerializationApi

internal class CharactersInfra {
    @OptIn(ExperimentalSerializationApi::class)
    private val api = RetrofitClient.createService(CharactersGateway::class.java)

    suspend fun listCharacters(): CharactersPresentation {
        val response = api.listCharacters()
        return CharacterMapper.characterToDomain(response)
    }
}