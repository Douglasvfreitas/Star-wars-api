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
): Serializable {

    fun retrieveEpisode(): Int {
        return when (episodeId) {
            4 -> R.string.episode_one_title
            5 -> R.string.episode_two_title
            6 -> R.string.episode_tree_title
            1 -> R.string.episode_four_title
            2 -> R.string.episode_five_title
            3 -> R.string.episode_six_title
            else -> R.string.unknown_episode_title
        }
    }
}
