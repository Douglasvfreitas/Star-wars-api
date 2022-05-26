package feature.characters.domain

import com.example.starwars.infra.models.character.CharactersPresentation
import com.example.starwars.infra.models.character.CharactersResponse
import com.example.starwars.models.Character

internal object CharacterMapper {

    fun characterToDomain(response: CharactersResponse): CharactersPresentation =
        CharactersPresentation(
            count = response.count,
            next = response.next,
            previous = response.previous,
            characters = response.result.map { characterResponse ->
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
                    homeWorld = characterResponse.homeworld,
                    urlImage = retrievePeopleImage(id),
                    id = id
                )
            }
        )
}

private fun retrieveCharacterId(url: String): String = url.filter { it.isDigit() }

private fun retrievePeopleImage(id: String): String {
    return "https://starwars-visualguide.com/assets/img/characters/$id.jpg"
}