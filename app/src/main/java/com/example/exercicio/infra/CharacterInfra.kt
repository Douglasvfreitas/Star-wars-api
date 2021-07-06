package com.example.exercicio.infra

import com.example.exercicio.infra.models.character.CharactersPresentation
import com.example.exercicio.models.Person
import com.example.exercicio.models.services.CharacterService
import io.reactivex.Observable

class CharacterInfra : CharacterService {
    private val api = RetrofitClient.createService(StarWarsGateway::class.java)

    override fun listCharacters(): Observable<CharactersPresentation> {
        return api.listPeople().map { charactersResponse ->
            CharactersPresentation(
                count = charactersResponse.count,
                next = charactersResponse.next,
                previous = charactersResponse.previous,
                persons = charactersResponse.result.map { characterResponse ->
                    val id: String = retrieveCharacterId(characterResponse.url)
                    Person(
                        name = characterResponse.name,
                        height = characterResponse.height,
                        mass = characterResponse.mass,
                        hairColor = characterResponse.hairColor,
                        skinColor = characterResponse.skinColor,
                        eyeColor = characterResponse.eyeColor,
                        birthYear = characterResponse.birthYear,
                        gender = characterResponse.gender,
                        homeworld = characterResponse.homeworld,
                        urlImage = retrievePeopleImage(id),
                        id = id
                    )
                }
            )
        }
    }


    private fun retrieveCharacterId(url: String): String = url.filter { it.isDigit() }

    private fun retrievePeopleImage(id: String): String {
        return "https://starwars-visualguide.com/assets/img/characters/$id.jpg"
    }

}