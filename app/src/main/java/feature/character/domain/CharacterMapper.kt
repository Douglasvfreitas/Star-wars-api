package feature.character.domain

import feature.ImageTypeResponse
import feature.character.data.models.CharacterDetailsResponse
import feature.character.data.models.CharactersResponse
import feature.character.domain.models.Character
import feature.character.domain.models.CharactersPresentation
import feature.utils.retrieveIdForImage
import feature.utils.retrieveImageById

internal object CharacterMapper {

    fun toDomain(response: CharactersResponse): CharactersPresentation =
        CharactersPresentation(
            count = response.count,
            next = response.next,
            previous = response.previous,
            characters = getCharacter(response.result)
        )

    private fun getCharacter(detailsResponse: List<CharacterDetailsResponse>): List<Character> {
        return detailsResponse.map { characterDetails ->
            val characterId = retrieveIdForImage(characterDetails.url)
            Character(
                name = characterDetails.name,
                height = characterDetails.height,
                mass = characterDetails.mass,
                hairColor = characterDetails.hairColor,
                skinColor = characterDetails.skinColor,
                eyeColor = characterDetails.eyeColor,
                birthYear = characterDetails.birthYear,
                gender = characterDetails.gender,
                homeworld = characterDetails.homeWorld,
                urlImage = retrieveImageById(characterId, ImageTypeResponse.CHARACTER_TYPE),
                id = characterId
            )
        }
    }
}