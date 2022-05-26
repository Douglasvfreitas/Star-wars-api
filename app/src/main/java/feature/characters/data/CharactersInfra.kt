package feature.characters.data

import com.example.starwars.infra.RetrofitClient
import com.example.starwars.infra.models.character.CharactersPresentation
import feature.characters.domain.CharacterMapper
import feature.characters.domain.services.CharactersService
import kotlinx.serialization.ExperimentalSerializationApi

internal class CharactersInfra : CharactersService {
    @OptIn(ExperimentalSerializationApi::class)
    private val api = RetrofitClient.createService(CharactersGateway::class.java)

    override suspend fun listCharacters(): CharactersPresentation {
        val response = api.listCharacters()
        return CharacterMapper.characterToDomain(response)
    }
}