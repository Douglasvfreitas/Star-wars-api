package feature.character.domain

import feature.character.domain.models.CharactersPresentation
import io.reactivex.Observable

interface CharacterService {
    fun listCharacters(): Observable<CharactersPresentation>
}