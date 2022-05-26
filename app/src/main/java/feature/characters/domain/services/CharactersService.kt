package feature.characters.domain.services

import com.example.starwars.infra.models.character.CharactersPresentation

internal interface CharactersService {
    suspend fun listCharacters(): CharactersPresentation
}