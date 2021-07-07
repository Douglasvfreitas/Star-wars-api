package com.example.exercicio.models.services

import com.example.exercicio.infra.models.character.CharactersPresentation
import io.reactivex.Observable

interface CharacterService {
    fun listCharacters(): Observable<CharactersPresentation>
}