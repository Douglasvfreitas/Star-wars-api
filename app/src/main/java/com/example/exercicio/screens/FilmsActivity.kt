package com.example.exercicio.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercicio.*
import com.example.exercicio.models.Film
import com.example.exercicio.models.ScreenState
import com.example.exercicio.params.FilmParams
import kotlinx.android.synthetic.main.activity_main.*


class FilmsActivity : AppCompatActivity() {

    private val viewModel by lazy { FilmsViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filmsRv.layoutManager = LinearLayoutManager(this)
        resultState()
        viewModel.retrieveMovies()
    }

    fun resultState() {
        viewModel.getScreenState().observe(this, Observer { screenState ->
            when (screenState) {
                is ScreenState.Error -> erroState()
                is ScreenState.Loading -> loadState(true)
                is ScreenState.Result -> handlerResult(screenState.value)
            }
        })
    }

    fun loadState(isLoading: Boolean) {
        progessBar.isVisible = isLoading
        filmsRv.isVisible = !isLoading
    }

    fun handlerResult(films: List<Film>) {
        loadState(false)
        filmsRv.adapter = FilmsAdapter(
            films = films, navigateToDetails = {
                moveFilmDetails(it)
            })
    }

    fun erroState() {
        Toast.makeText(this, ERROR_MENSSAGE, Toast.LENGTH_SHORT).show()
        loadState(false)
    }


    fun moveFilmDetails(film: Film) {
        startActivity(Intent(this, FilmDetailsActivity::class.java).apply {
            putExtra(FilmParams.FILM_FILM, film)
        })
    }

    companion object {
        const val ERROR_MENSSAGE = "Erro no carregamento das informações"
    }

}


