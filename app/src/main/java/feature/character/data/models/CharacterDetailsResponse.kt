package feature.character.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailsResponse (
    @SerialName("name") var name: String = "",
    @SerialName("height") var height: String = "",
    @SerialName("mass") var mass: String = "",
    @SerialName("hair_color") var hairColor: String = "",
    @SerialName("skin_color") var skinColor: String = "",
    @SerialName("eye_color") var eyeColor: String = "",
    @SerialName("birth_year") var birthYear: String = "",
    @SerialName("gender") var gender: String = "",
    @SerialName("homeworld") var homeworld: String = "",
    @SerialName("url") var url: String
)