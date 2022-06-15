package feature.planet.domain.models

import java.io.Serializable

data class Planet(
    val name: String,
    val rotation: String,
    val orbital: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val water: String,
    val population: String,
    val urlImage: String,
    val id: String
): Serializable
