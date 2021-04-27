package com.example.exercicio.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio.models.Film
import com.example.exercicio.params.FilmParams
import com.example.exercicio.R
import kotlinx.android.synthetic.main.activity_film_details.*

class FilmDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_details)

        setupView()
    }

    fun setupView() {
        val film = intent.extras?.getSerializable(FilmParams.FILM_FILM) as? Film

        film?.let {
            filmImageDet.setImageResource(film.defImage())
            filmTitleTv.text = film.title
            filmOpening.text = film.openingCrawl
            directorFilm.text = film.director
            producerFilm.text = film.producer
        }
    }
}