package com.example.exercicio.infra

import com.example.exercicio.infra.mappers.CharacterMapper
import com.example.exercicio.infra.models.character.CharactersPresentation
import com.example.exercicio.models.Person
import com.example.exercicio.models.services.CharacterService
import io.reactivex.Observable

class CharacterInfra : CharacterService {
    private val api = RetrofitClient.createService(StarWarsGateway::class.java)

    override fun listCharacters(): Observable<CharactersPresentation> {
        return api.listPeople().map { charactersResponse ->
            CharacterMapper.characterToDomain(charactersResponse)
        }
    }
}