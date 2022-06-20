package feature.planet.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetDetailsResponse(
    @SerialName("name") val name: String,
    @SerialName("rotation_period") val rotation: String,
    @SerialName("orbital_period") val orbital: String,
    @SerialName("diameter") val diameter: String,
    @SerialName("climate") val climate: String,
    @SerialName("gravity") val gravity: String,
    @SerialName("terrain") val terrain: String,
    @SerialName("surface_water") val water: String,
    @SerialName("population") val population: String,
    @SerialName("url") val url: String
):java.io.Serializable