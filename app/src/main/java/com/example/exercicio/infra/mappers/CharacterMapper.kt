package com.example.exercicio.infra.mappers

import com.example.exercicio.infra.RetrofitClient
import com.example.exercicio.infra.StarWarsGateway
import com.example.exercicio.infra.models.character.CharactersPresentation
import com.example.exercicio.infra.models.character.CharactersResponse
import com.example.exercicio.models.Person
import io.reactivex.Observable

internal object CharacterMapper {
    private const val CHARACTERS_URL = "https://starwars-visualguide.com/assets/img/characters/"
    private const val JPG_EXTENSION = ".jpg"

    fun characterToDomain(charactersResponse: CharactersResponse): CharactersPresentation {
        return CharactersPresentation(
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

    private fun retrieveCharacterId(url: String): String = url.filter { it.isDigit() }

    private fun retrievePeopleImage(id: String): String {
        return "$CHARACTERS_URL$id$JPG_EXTENSION"
    }
}
