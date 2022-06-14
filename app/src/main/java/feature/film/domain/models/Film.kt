package feature.film.domain.models

import com.example.starwars.R
import java.io.Serializable


data class Film(
    val title: String,
    val episodeId: Int,
    val openingCrawl: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val characters: List<String> = emptyList(),
    val url: String,
    val id: String

) : Serializable {

    fun retrieveEpisodeImage(): Int {
        return when (episodeId) {
            4 -> R.drawable.episode1
            5 -> R.drawable.episode2
            6 -> R.drawable.episode3
            1 -> R.drawable.episode4
            2 -> R.drawable.episode5
            else -> R.drawable.episode6
        }
    }

    fun retrieveEpisode(): String {
        return when (episodeId) {
            4 -> "Episode I"
            5 -> "Episode II"
            6 -> "Episode III"
            1 -> "Episode IV"
            2 -> "Episode V"
            else -> "Episode VI"
        }
    }
}
