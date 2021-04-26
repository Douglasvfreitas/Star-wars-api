package com.example.exercicio

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmResponse(
        @SerialName("title") var title: String = "",
        @SerialName("episode_id") var episodeId: Int,
        @SerialName("opening_crawl") var openingCrawl: String = "",
        @SerialName("director") var director: String = "",
        @SerialName("producer") var producer: String = "",
        @SerialName("release_date") var releaseDate: String = "",
        @SerialName("characters") var characters: List<String>? = emptyList(),
        @SerialName("planets") var planets: List<String>? = emptyList(),
        @SerialName("starships") var starships: List<String>? = emptyList(),
        @SerialName("vehicles") var vehicles: List<String>? = emptyList(),
        @SerialName("species") var species: List<String>? = emptyList(),
        @SerialName("created") var created: String = "",
        @SerialName("edited") var edited: String = "",
        @SerialName("url") var url: String = ""
)