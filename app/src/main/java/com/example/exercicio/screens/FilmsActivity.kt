package com.example.exercicio.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercicio.*
import com.example.exercicio.infra.StarWarsGateway
import com.example.exercicio.models.Film
import com.example.exercicio.models.ScreenState
import com.example.exercicio.params.FilmParams
import com.example.exercicio.infra.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class FilmsActivity : AppCompatActivity() {


    private val remote = RetrofitClient.createService(StarWarsGateway::class.java)
    private val disposer = CompositeDisposable()
    private val filmState = MutableLiveData<ScreenState<List<Film>>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filmsRv.layoutManager = LinearLayoutManager(this)
        resultState()
        retrieveMovies()

    }

    override fun onDestroy() {
        super.onDestroy()
        disposer.clear()
    }

    fun resultState() {
        filmState.observe(this, Observer { screenState ->
            when (screenState){
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

    fun retrieveMovies() {
        filmState.value = ScreenState.Loading
        disposer += remote.listMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { filmsResponse ->
                    filmsResponse.result.map { filmModel ->
                        Film(
                            title = filmModel.title,
                            episodeId = filmModel.episodeId,
                            openingCrawl = filmModel.openingCrawl,
                            director = filmModel.director,
                            producer = filmModel.producer,
                            releaseDate = filmModel.releaseDate,
                            characters = filmModel.characters.orEmpty(),
                            url = filmModel.url
                        )
                    }
                        .let { films ->
                            filmState.value = ScreenState.Result(films)
                        }
                },
                onError = { error ->
                    filmState.value = ScreenState.Error(error)
                }
            )
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


