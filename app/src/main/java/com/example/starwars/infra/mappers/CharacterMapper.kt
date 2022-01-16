package com.example.starwars.infra.mappers

import com.example.starwars.infra.RetrofitClient
import com.example.starwars.infra.StarWarsGateway
import com.example.starwars.infra.models.character.CharactersPresentation
import com.example.starwars.models.Character
import io.reactivex.Observable

internal object CharacterMapper {
    private val api = RetrofitClient.createService(StarWarsGateway::class.java)

    fun characterToDomain(): Observable<CharactersPresentation> {
        return api.listPeople().map { charactersResponse ->
            CharactersPresentation(
                count = charactersResponse.count,
                next = charactersResponse.next,
                previous = charactersResponse.previous,
                characters = charactersResponse.result.map { characterResponse ->
                    val id: String = retrieveCharacterId(characterResponse.url)
                    Character(
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
}

private fun retrieveCharacterId(url: String): String = url.filter { it.isDigit() }

private fun retrievePeopleImage(id: String): String {
    return "https://starwars-visualguide.com/assets/img/characters/$id.jpg"
}