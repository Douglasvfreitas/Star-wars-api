package feature.film.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.starwars.R
import feature.film.domain.models.Film
import feature.Params
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_details.*
import kotlinx.android.synthetic.main.activity_film_details.*

class FilmDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_details)

        setupView()
    }

    private fun setupView() {
        val film = intent.extras?.getSerializable(Params.FILM_FILM) as? Film

        film?.let {
            setImage(film.url)
            filmTitleTv.text = getFilmTitle(film)
            filmOpening.text = film.openingCrawl
            directorFilm.text = film.director
            producerFilm.text = film.producer
        }
    }

    private fun setImage(urlImage: String) {
        Picasso
            .get()
            .load(urlImage)
            .error(R.drawable.episode1)
            .into(filmImageDet)
    }

    private fun getFilmTitle(film: Film): String {
        return """${film.retrieveEpisode()}: ${film.title}"""
    }
}