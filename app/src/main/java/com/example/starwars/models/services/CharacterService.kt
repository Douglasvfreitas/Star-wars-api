package com.example.starwars.models.services

import com.example.starwars.infra.models.character.CharactersPresentation
import io.reactivex.Observable

interface CharacterService {
    fun listCharacters(): Observable<CharactersPresentation>
}