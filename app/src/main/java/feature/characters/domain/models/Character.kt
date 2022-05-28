package feature.characters.domain.models

import java.io.Serializable

data class Character(
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val homeWorld: String,
    val urlImage: String,
    val id: String
) : Serializable