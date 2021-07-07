package com.example.exercicio.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio.R
import com.example.exercicio.models.Film
import com.example.exercicio.params.Params
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
            filmImageDet.setImageResource(film.retrieveEpisodeImage())
            filmTitleTv.text = getFilmTitle(film)
            filmOpening.text = film.openingCrawl
            directorFilm.text = film.director
            producerFilm.text = film.producer
        }
    }

    private fun getFilmTitle(film: Film): String {
        return """${film.retrieveEpisode()}: ${film.title}"""
    }
}