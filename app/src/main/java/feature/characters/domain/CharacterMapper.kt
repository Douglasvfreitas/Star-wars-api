package feature.characters.domain

import feature.characters.presentation.models.CharactersPresentation
import feature.characters.data.models.CharactersResponse
import feature.characters.domain.models.Character
import feature.utils.retrieveIdForImage
import feature.utils.retrieveImageByUrl

internal object CharacterMapper {

    fun characterToDomain(response: CharactersResponse): CharactersPresentation =
        CharactersPresentation(
            count = response.count,
            next = response.next,
            previous = response.previous,
            characters = response.result.map { characterResponse ->
                val id: String = retrieveIdForImage(characterResponse.url)
                Character(
                    name = characterResponse.name,
                    height = characterResponse.height,
                    mass = characterResponse.mass,
                    hairColor = characterResponse.hairColor,
                    skinColor = characterResponse.skinColor,
                    eyeColor = characterResponse.eyeColor,
                    birthYear = characterResponse.birthYear,
                    gender = characterResponse.gender,
                    homeWorld = characterResponse.homeWorld,
                    urlImage = retrieveImageByUrl(id,TYPE_RESPONSE),
                    id = id
                )
            }
        )

    private const val TYPE_RESPONSE = "characters"
}