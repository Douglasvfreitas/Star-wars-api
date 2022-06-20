package feature.film.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDetailsResponse(
    @SerialName("title") val title: String = "",
    @SerialName("episode_id") val episodeId: Int,
    @SerialName("opening_crawl") val openingCrawl: String = "",
    @SerialName("director") val director: String = "",
    @SerialName("producer") val producer: String = "",
    @SerialName("release_date") val releaseDate: String = "",
    @SerialName("characters") val characters: List<String>? = emptyList(),
    @SerialName("planets") val planets: List<String>? = emptyList(),
    @SerialName("starships") val starships: List<String>? = emptyList(),
    @SerialName("vehicles") val vehicles: List<String>? = emptyList(),
    @SerialName("species") val species: List<String>? = emptyList(),
    @SerialName("created") val created: String = "",
    @SerialName("edited") val edited: String = "",
    @SerialName("url") val url: String = ""
)