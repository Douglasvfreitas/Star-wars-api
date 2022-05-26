package com.example.starwars.screens.film

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.starwars.R
import com.example.starwars.models.Film
import com.example.starwars.models.ScreenState
import com.example.starwars.params.Params
import kotlinx.android.synthetic.main.activity_films.*

class FilmsActivity : AppCompatActivity() {

    private val viewModel by lazy { FilmsViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        handleScreenStates()
        viewModel.retrieveMovies()
        listenButton()
    }

    private fun handleScreenStates() {
        viewModel.getScreenState().observe(this) { screenState ->
            when (screenState) {
                is ScreenState.Error -> handleError()
                is ScreenState.Loading -> handleLoading(true)
                is ScreenState.Result -> handleResult(screenState.value)
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        loadingState.isVisible = isLoading
        filmsRv.isVisible = !isLoading
        retryButton.isVisible = false
    }

    private fun listenButton() {
        retryButton.setOnClickListener {
            viewModel.retrieveMovies()
        }
    }

    private fun handleResult(films: List<Film>) {
        handleLoading(false)
        filmsRv.adapter = FilmsAdapter(
            films = films, navigateToDetails = { film ->
                moveToFilmDetails(film)
            }
        )
    }

    private fun handleError() {
        Toast.makeText(this, ERROR_MENSSAGE, Toast.LENGTH_LONG).apply {
            setGravity(Gravity.CENTER, 0, 0)
            show()
        }
        handleLoading(false)
        retryButton.isVisible = true
    }

    private fun moveToFilmDetails(film: Film) {
        startActivity(
            Intent(this, FilmDetailsActivity::class.java).apply {
                putExtra(Params.FILM_FILM, film)
            }
        )
    }

    companion object {
        const val ERROR_MENSSAGE = "Sentimos muito, ocorreu um erro"
    }
}